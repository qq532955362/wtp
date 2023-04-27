#!/bin/bash

create_servicediscovery(){
 SUM=`aws servicediscovery list-services --region $AWS_DEFAULT_REGION|grep Name|awk -F ": \"" '{print$2}' |awk -F "\"," '{print$1}' |grep ^${SERVICEDISCOVERY_NAME}|wc -l`
 if [ $SUM -eq 0 ];then
   REGISTRYARN=`aws servicediscovery create-service --name ${SERVICEDISCOVERY_NAME} --dns-config 'NamespaceId="ns-hcmijrjjvph34ckx",DnsRecords=[{Type="A",TTL="30"}]' --health-check-custom-config FailureThreshold=1 --region $AWS_DEFAULT_REGION --output text |awk '{print$2}'|grep arn`
   echo $REGISTRYARN >/tmp/REGISTRYARN
 else
   echo "*************************************"
   echo "${SERVICEDISCOVERY_NAME} servicediscovery exists !!!"
   echo "*************************************"
 fi
}

create_ecr_repository(){
 sum=`aws ecr describe-repositories --region $AWS_DEFAULT_REGION|grep epositoryName |grep $BUSINESS_GROUP/$ECR_REPOSITORY|awk -F "\"" '{print$4}'|grep ^$BUSINESS_GROUP/$ECR_REPOSITORY|wc -l`
 if [ $sum -eq 0 ];then
   aws ecr create-repository --repository-name $BUSINESS_GROUP/$ECR_REPOSITORY --image-scanning-configuration scanOnPush=true --region $AWS_DEFAULT_REGION
 else
   echo "*************************************"
   echo "$BUSINESS_GROUP/$ECR_REPOSITORY Repository exists !!!"
   echo "*************************************"
 fi
}

update_ecs_fargate_task_jvm(){
if [ $(echo "$VMEMORY < 1.0"|bc) -eq 1 ];then
   MEMORY=500
else
   MEMORY=`expr $VMEMORY \* 1000`
fi
cat >create-fargate-task.json<<EOF
      {
          "taskRoleArn":"ecsTaskExecutionRole",
          "executionRoleArn":"ecsTaskExecutionRole",
          "family":"$SERVICE_NAME",
          "networkMode":"awsvpc",
          "containerDefinitions":[
          {
                  "name": "aws-otel-collector",
                  "image": "public.ecr.aws/aws-observability/aws-otel-collector:v0.17.0",
                  "cpu": 0,
                  "essential": false,
                  "command": [
                      "--config=/etc/ecs/ecs-amp-prometheus.yaml"
                  ],
                  "environment": [
                      {
                          "name": "AWS_PROMETHEUS_SCRAPING_ENDPOINT",
                          "value": "0.0.0.0:$SEVICE_PORT"
                      },
                      {
                          "name": "AWS_PROMETHEUS_ENDPOINT",
                          "value": "https://aps-workspaces.us-east-1.amazonaws.com/workspaces/ws-e1cd5657-915d-46e7-9307-ff327665db97/api/v1/remote_write"
                      }
                  ],
                  "logConfiguration": {
                      "logDriver": "awslogs",
                      "options": {
                          "awslogs-create-group": "true",
                          "awslogs-group": "/ecs/ecs-aws-otel-sidecar-collector",
                          "awslogs-region": "${AWS_DEFAULT_REGION}",
                          "awslogs-stream-prefix": "ecs"
                      }
                  }
              },
        {
            "name":"$SERVICE_NAME",
            "image":"${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/$BUSINESS_GROUP/$ECR_REPOSITORY:$ENV",
            "portMappings":[
                {
                    "containerPort":$SEVICE_PORT,
                    "hostPort":$SEVICE_PORT,
                    "protocol":"tcp"
                }
            ],
            "memory":$MEMORY,
            "memoryReservation":$MEMORY,
            "essential":true,
            "entryPoint":[
                "sh",
                "-c"
            ],
            "command":[
                "$START_COMMAND"
            ],
            "logConfiguration":{
                "logDriver":"awslogs",
                "options":{
                    "awslogs-group":"/ecs/$BUSINESS_GROUP/$SERVICE_NAME",
                    "awslogs-region":"${AWS_DEFAULT_REGION}",
                    "awslogs-stream-prefix":"ecs",
                    "awslogs-create-group": "true"
                }
            }
        }
    ],
    "requiresCompatibilities":[
        "FARGATE"
    ],
    "cpu":"${VCPU}vCPU",
    "memory":"${VMEMORY}GB"
}
EOF
aws ecs register-task-definition --cli-input-json file://create-fargate-task.json --region ${AWS_DEFAULT_REGION}
}

create_ecs_fargate_task(){
sum=`aws ecs list-task-definitions --region ${AWS_DEFAULT_REGION}|grep $SERVICE_NAME|awk -F "/" '{print$2}'|awk -F ":" '{print$1}'|grep ^$SERVICE_NAME|wc -l`
if [ $sum -eq 0 ];then
   if [[ -z "$VCPU" ]] || [[ -z "$VMEMORY" ]];then
     if [[ "$ENV" == "dev" ]] || [[ "$ENV" == "test" ]] || [[ "$ENV" == "pda" ]];then
       if [[ $LANGUAGE == "nodejs" ]];then
         local VCPU="0.25"
         local VMEMORY="0.5"
       else
         local VCPU="1"
         local VMEMORY="3"
       fi
     else
        local VCPU="2"
        local VMEMORY="4"
     fi
   else
     if [ $(echo "$VMEMORY < 1.0"|bc) -eq 1 ];then
        MEMORY=500
     else
        MEMORY=`expr $VMEMORY \* 1000`
     fi
   fi
cat >create-fargate-task.json<<EOF
{
    "taskRoleArn":"ecsTaskExecutionRole",
    "executionRoleArn":"ecsTaskExecutionRole",
    "family":"$SERVICE_NAME",
    "networkMode":"awsvpc",
    "containerDefinitions":[
        {
            "name":"$SERVICE_NAME",
            "image":"${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/$BUSINESS_GROUP/$ECR_REPOSITORY:$ENV",
            "portMappings":[
                {
                    "containerPort":$SEVICE_PORT,
                    "hostPort":$SEVICE_PORT,
                    "protocol":"tcp"
                }
            ],
            "essential":true,
            "entryPoint":[
                "sh",
                "-c"
            ],
            "command":[
                "$START_COMMAND"
            ],
            "logConfiguration":{
                "logDriver":"awslogs",
                "options":{
                    "awslogs-group":"/ecs/$BUSINESS_GROUP/$SERVICE_NAME",
                    "awslogs-region":"${AWS_DEFAULT_REGION}",
                    "awslogs-stream-prefix":"ecs",
                    "awslogs-create-group": "true"
                }
            }
        }
    ],
    "requiresCompatibilities":[
        "FARGATE"
    ],
    "cpu":"${VCPU}vCPU",
    "memory":"${VMEMORY}GB"
}
EOF
#cat create-fargate-task.json
aws ecs register-task-definition --cli-input-json file://create-fargate-task.json --region ${AWS_DEFAULT_REGION}
else
   echo "*************************************"
   echo "ECS $SERVICE_NAME task exists !!!"
   echo "*************************************"
fi
}

#create_alb()创建目标组和策略
create_alb(){
  #查询所有目标组中是否存在所需的目标组
  local target_sum=`aws elbv2 describe-target-groups --region $AWS_DEFAULT_REGION|grep TargetGroupName|grep $SERVICE_NAME|awk -F "\"" '{print$4}' |grep ^$SERVICE_NAME|wc -l`
  #查询最大策略编号
  local PARIORITY_NUMBER=`aws elbv2 describe-rules --region $AWS_DEFAULT_REGION --listener-arn "$LISTENER_ARN"|grep Priority|awk -F "\"" '{print$4}'|grep "^[0-9][0-9]*$"|tail -1`
  if [ "$PARIORITY_NUMBER" -gt 0 ] 2>/dev/null ;then
     PARIORITY_NUMBER=`expr $PARIORITY_NUMBER + 1`
  else
     PARIORITY_NUMBER=1
  fi

  if [ $target_sum -eq 0 ];then
    TARGETGROUPAR=`aws elbv2 create-target-group \
         --name $SERVICE_NAME \
         --protocol HTTP \
         --port 80 \
         --target-type ip \
         --vpc-id $ALB_VPC_ID \
         --health-check-path /ping \
         --region $AWS_DEFAULT_REGION|grep TargetGroupArn|awk -F "\"" '{print$4}'`
    echo $TARGETGROUPAR >/tmp/TGAR

    if [[ -n "$DOMAIN_NAME" ]];then
      aws elbv2 create-rule \
      --listener-arn $LISTENER_ARN \
      --conditions Field=host-header,Values=$DOMAIN_NAME \
      --priority $PARIORITY_NUMBER \
      --actions Type=forward,TargetGroupArn=$TARGETGROUPAR \
      --region $AWS_DEFAULT_REGION
    fi

    if [[ -n "$PATH_PATTERN" ]];then
      aws elbv2 create-rule \
      --listener-arn $LISTENER_ARN \
      --conditions Field=path-pattern,Values=$PATH_PATTERN \
      --priority $PARIORITY_NUMBER \
      --actions Type=forward,TargetGroupArn=$TARGETGROUPAR \
      --region $AWS_DEFAULT_REGION
    fi
  else
    echo "*************************************"
    echo "ALB Rule exists !!!"
    echo "*************************************"
  fi
}

conf_alb(){
  #查询特定ALB相关信息
  local ALB_VPC_ID=`aws elbv2 describe-load-balancers --names $LB_NAME |grep VpcId|awk -F "\"" '{print$4}'`
  local LB_ARN=`aws elbv2 describe-load-balancers --names $LB_NAME |grep LoadBalancerArn|awk -F "\"" '{print$4}'`
  aws elbv2 describe-load-balancers --names $LB_NAME |grep DNSName|awk -F "\"" '{print$4}' >/tmp/DNSNAME
  aws elbv2 describe-load-balancers --names $LB_NAME |grep CanonicalHostedZoneId|awk -F "\"" '{print$4}' >/tmp/ALB_ID
  aws elbv2 describe-load-balancers --names $LB_NAME |grep SubnetId|awk -F ":" '{print$2}'|tr "\n" " "|awk -F ", $" '{print$1}'|sed "s/ //g" >/tmp/SERVICE_SUBNETS

  if [ "$PROTOCOL"x == "http"x ];then
    local LISTENER_ARN=`aws elbv2 describe-listeners --region $AWS_DEFAULT_REGION --load-balancer-arn "$LB_ARN"|grep 80 -B2|grep ListenerArn|awk -F "\"" '{print$4}'`
    create_alb
  elif [[ -z $PROTOCOL ]];then
    local LISTENER_ARN=`aws elbv2 describe-listeners --region $AWS_DEFAULT_REGION --load-balancer-arn "$LB_ARN"|grep 443 -B2|grep ListenerArn|awk -F "\"" '{print$4}'`
    create_alb
  else
    echo "新协议请通知运维更新"
  fi
}

conf_alb_http(){
target_sum=`aws elbv2 describe-target-groups --region $AWS_DEFAULT_REGION|grep TargetGroupName|grep $SERVICE_NAME|awk -F "\"" '{print$4}' |grep ^$SERVICE_NAME|wc -l`
aws elbv2 describe-listeners --region $AWS_DEFAULT_REGION --load-balancer-arn `aws elbv2 describe-load-balancers --region $AWS_DEFAULT_REGION|grep $LB_NAME|grep LoadBalancerArn|awk -F "\"" '{print$4}'`|grep -E "(ListenerArn| 80)" >listen
sum=$(expr `grep -n ": 80" listen|awk -F ":" '{print$1}'`-1|bc)
LISTENER_ARN=`sed -n "$sum,1p" listen|awk -F "\"" '{print$4}'`
PARIORITY_NUMBER=`aws elbv2 describe-rules --region $AWS_DEFAULT_REGION --listener-arn $LISTENER_ARN|grep Priority|tail -n 2|head -1|awk -F "\"" '{print$4}'`

if [ "$PARIORITY_NUMBER" -gt 0 ] 2>/dev/null ;then
   PARIORITY_NUMBER=`expr $PARIORITY_NUMBER + 1`
else
   PARIORITY_NUMBER=1
fi
#PARIORITY_NUMBER=`expr $PARIORITY_NUMBER + 1`
aws elbv2 describe-load-balancers --region $AWS_DEFAULT_REGION|grep -E "(LoadBalancerName|VpcId)" >lbvpc
sum=$(expr `cat lbvpc|grep $LB_NAME -n|awk -F ":" '{print$1}'`+1|bc)
ALB_VPC_ID=`sed -n "$sum,1p" lbvpc|awk -F ":" '{print$2}'|awk -F "\"" '{print$2}'`

if [ $target_sum -eq 0 ];then
  TARGETGROUPAR=`aws elbv2 create-target-group \
       --name $SERVICE_NAME \
       --protocol HTTP \
       --port 80 \
       --target-type ip \
       --vpc-id $ALB_VPC_ID \
       --health-check-path /ping \
       --region $AWS_DEFAULT_REGION|grep TargetGroupArn|awk -F "\"" '{print$4}'`
  echo $TARGETGROUPAR >/tmp/TGAR

  aws elbv2 create-rule \
  --listener-arn $LISTENER_ARN \
  --conditions Field=path-pattern,Values=$PATH_PATTERN \
  --priority $PARIORITY_NUMBER \
  --actions Type=forward,TargetGroupArn=$TARGETGROUPAR \
  --region $AWS_DEFAULT_REGION
else
   echo "*************************************"
   echo "ALB Rule exists !!!"
   echo "*************************************"
fi
}

conf_alb_https(){
target_sum=`aws elbv2 describe-target-groups --region $AWS_DEFAULT_REGION|grep TargetGroupName|grep $SERVICE_NAME|awk -F "\"" '{print$4}' |grep ^$SERVICE_NAME|wc -l`
aws elbv2 describe-listeners --region $AWS_DEFAULT_REGION --load-balancer-arn `aws elbv2 describe-load-balancers --region $AWS_DEFAULT_REGION|grep $LB_NAME|grep LoadBalancerArn|awk -F "\"" '{print$4}'`|grep -E "(ListenerArn| 443)" >listen
sum=$(expr `grep -n ": 443" listen|awk -F ":" '{print$1}'`-1|bc)
LISTENER_ARN=`sed -n "$sum,1p" listen|awk -F "\"" '{print$4}'`
PARIORITY_NUMBER=`aws elbv2 describe-rules --region $AWS_DEFAULT_REGION --listener-arn $LISTENER_ARN|grep Priority|tail -n 2|head -1|awk -F "\"" '{print$4}'`

if [ "$PARIORITY_NUMBER" -gt 0 ] 2>/dev/null ;then
   PARIORITY_NUMBER=`expr $PARIORITY_NUMBER + 1`
else
   PARIORITY_NUMBER=1
fi
#PARIORITY_NUMBER=`expr $PARIORITY_NUMBER + 1`
aws elbv2 describe-load-balancers --region $AWS_DEFAULT_REGION|grep -E "(LoadBalancerName|VpcId)" >lbvpc
sum=$(expr `cat lbvpc|grep $LB_NAME -n|awk -F ":" '{print$1}'`+1|bc)
ALB_VPC_ID=`sed -n "$sum,1p" lbvpc|awk -F ":" '{print$2}'|awk -F "\"" '{print$2}'`

if [ $target_sum -eq 0 ];then
TARGETGROUPAR=`aws elbv2 create-target-group \
     --name $SERVICE_NAME \
     --protocol HTTP \
     --port 80 \
     --target-type ip \
     --vpc-id $ALB_VPC_ID \
     --health-check-path /ping \
     --region $AWS_DEFAULT_REGION|grep TargetGroupArn|awk -F "\"" '{print$4}'`
echo $TARGETGROUPAR >/tmp/TGAR
aws elbv2 create-rule \
--listener-arn $LISTENER_ARN \
--conditions Field=path-pattern,Values=$PATH_PATTERN \
--priority $PARIORITY_NUMBER \
--actions Type=forward,TargetGroupArn=$TARGETGROUPAR \
--region $AWS_DEFAULT_REGION
else
   echo "*************************************"
   echo "ALB Rule exists !!!"
   echo "*************************************"
fi
}

conf_alb_grpc(){
target_sum=`aws elbv2 describe-target-groups --region $AWS_DEFAULT_REGION|grep TargetGroupName|grep $SERVICE_NAME|awk -F "\"" '{print$4}' |grep ^$SERVICE_NAME|wc -l`
aws elbv2 describe-listeners --region $AWS_DEFAULT_REGION --load-balancer-arn `aws elbv2 describe-load-balancers --region $AWS_DEFAULT_REGION|grep $LB_NAME|grep LoadBalancerArn|awk -F "\"" '{print$4}'`|grep -E "(ListenerArn| 443)" >listen
sum=$(expr `grep -n ": 443" listen|awk -F ":" '{print$1}'`-1|bc)
LISTENER_ARN=`sed -n "$sum,1p" listen|awk -F "\"" '{print$4}'`
PARIORITY_NUMBER=`aws elbv2 describe-rules --region $AWS_DEFAULT_REGION --listener-arn $LISTENER_ARN|grep Priority|tail -n 2|head -1|awk -F "\"" '{print$4}'`
PARIORITY_NUMBER=`expr $PARIORITY_NUMBER + 1`
aws elbv2 describe-load-balancers --region $AWS_DEFAULT_REGION|grep -E "(LoadBalancerName|VpcId)" >lbvpc
sum=$(expr `cat lbvpc|grep $LB_NAME -n|awk -F ":" '{print$1}'`+1|bc)
ALB_VPC_ID=`sed -n "$sum,1p" lbvpc|awk -F ":" '{print$2}'|awk -F "\"" '{print$2}'`

if [ $target_sum -eq 0 ];then
TARGETGROUPAR=`aws elbv2 create-target-group \
     --name $SERVICE_NAME \
     --protocol HTTP \
     --protocol-version GRPC \
     --port 50051 \
     --target-type ip \
     --vpc-id $ALB_VPC_ID \
     --health-check-path /common.ping/Ping \
     --matcher GrpcCode=0 \
     --region $AWS_DEFAULT_REGION|grep TargetGroupArn|awk -F "\"" '{print$4}'`
echo $TARGETGROUPAR >/tmp/TGAR
aws elbv2 create-rule \
--listener-arn $LISTENER_ARN \
--conditions Field=host-header,Values=$DOMAIN_NAME \
--priority $PARIORITY_NUMBER \
--actions Type=forward,TargetGroupArn=$TARGETGROUPAR \
--region $AWS_DEFAULT_REGION
else
   echo "*************************************"
   echo "ALB LISTENER exists !!!"
   echo "*************************************"
fi
}

create_ecs_fargate_grpc_service(){
sum=`aws ecs list-services --cluster $CLUSTER_NAME --region $AWS_DEFAULT_REGION|grep $SERVICE_NAME|awk -F "$CLUSTER_NAME/" '{print$2}'|awk -F "\"" '{print$1}'|wc -l`
if [ $sum -eq 0 ];then
TARGETGROUPAR=`cat /tmp/TGAR`
REGISTRYARN=`cat /tmp/REGISTRYARN`
SERVICE_SUBNETS=`cat /tmp/SERVICE_SUBNETS`
cat >create-service.json<<EOF
{
    "cluster":"$CLUSTER_NAME",
    "serviceName":"$SERVICE_NAME",
    "taskDefinition":"$SERVICE_NAME",
    "serviceRegistries": [
                {
                    "registryArn": "$REGISTRYARN"
                }
            ],
    "loadBalancers":[
        {
            "targetGroupArn":"$TARGETGROUPAR",
            "containerName":"$SERVICE_NAME",
            "containerPort":$SEVICE_PORT
        }
    ],
    "launchType":"FARGATE",
    "platformVersion":"1.4.0",
    "networkConfiguration":{
        "awsvpcConfiguration":{
            "assignPublicIp":"ENABLED",
            "subnets":[
                $SERVICE_SUBNETS
            ],
            "securityGroups":[
                "$SERVICE_SG"
            ]
        }
    },
    "healthCheckGracePeriodSeconds": 15,
    "desiredCount":$DESIREDCOUNT
}
EOF
#cat create-service.json
aws ecs create-service --cli-input-json file://create-service.json --region $AWS_DEFAULT_REGION
else
   echo "*************************************"
   echo "$SERVICE_NAME Sevice exists !!!"
   echo "*************************************"
fi
}

create_ecs_fargate_service(){
SERVICE_SUBNETS=`cat /tmp/SERVICE_SUBNETS`
us_test=`grep subnet-03821f4871c8f92f9 /tmp/SERVICE_SUBNETS|wc -l`
ap_test=`grep sg-0ee4f7c46c1e4d9bc /tmp/SERVICE_SUBNETS|wc -l`
us_pro1=`grep subnet-ebd4a28e /tmp/SERVICE_SUBNETS|wc -l`
us_pro2=`grep subnet-085a3fdf36d5b77c4 /tmp/SERVICE_SUBNETS|wc -l`
if [[ "$ENV" == "dev" ]] || [[ "$ENV" == "test" ]] || [[ "$ENV" == "pda" ]];then
  if [[ "$us_test" -eq 1 ]];then
    local SERVICE_SG="sg-00995a6fcf5e38697"
    local DESIREDCOUNT=1
  elif [ "$ap_test" -eq 1 ]; then
    local SERVICE_SG="sg-0ee4f7c46c1e4d9bc"
    local DESIREDCOUNT=1
  else
    local SERVICE_SG="$SERVICE_SG"
    local DESIREDCOUNT="$DESIREDCOUNT"
  fi
else
  if [[ "$ENV" == "pre" ]] && [[ $us_pro1 -eq 1 ]];then
    local SERVICE_SG="sg-dc67b7a3"
    local DESIREDCOUNT=1
  elif [[ "$ENV" == "pre" ]] && [[ $us_pro2 -eq 1 ]];then
    local SERVICE_SG="sg-07883bcc1758fe878"
    local DESIREDCOUNT=1
  elif [[ $us_pro1 -eq 1 ]];then
    local SERVICE_SG="sg-dc67b7a3"
    local DESIREDCOUNT=2
  elif [ $us_pro2 -eq 1 ]; then
    local SERVICE_SG="sg-07883bcc1758fe878"
    local DESIREDCOUNT=2
  elif [ $ap_test -eq 1 ]; then
    local SERVICE_SG="sg-0ee4f7c46c1e4d9bc"
    local DESIREDCOUNT=2
  else
    local SERVICE_SG="$SERVICE_SG"
    local DESIREDCOUNT="$DESIREDCOUNT"
  fi
fi
if [[ -z $HEALTH_CHECK ]];then
  HEALTH_CHECK=30
elif [ "$LANGUAGE" == "nodejs" ]; then
  HEALTH_CHECK=15
else
  HEALTH_CHECK=$HEALTH_CHECK
fi
sum=`aws ecs list-services --cluster $CLUSTER_NAME --region $AWS_DEFAULT_REGION|grep $SERVICE_NAME|awk -F "$CLUSTER_NAME/" '{print$2}'|awk -F "\"" '{print$1}'|wc -l`
if [ $sum -eq 0 ];then
  TARGETGROUPAR=`cat /tmp/TGAR`
cat >create-service.json<<EOF
{
    "cluster":"$CLUSTER_NAME",
    "serviceName":"$SERVICE_NAME",
    "taskDefinition":"$SERVICE_NAME",
    "loadBalancers":[
        {
            "targetGroupArn":"$TARGETGROUPAR",
            "containerName":"$SERVICE_NAME",
            "containerPort":$SEVICE_PORT
        }
    ],
    "launchType":"FARGATE",
    "platformVersion":"1.4.0",
    "networkConfiguration":{
        "awsvpcConfiguration":{
            "assignPublicIp":"ENABLED",
            "subnets":[
                $SERVICE_SUBNETS
            ],
            "securityGroups":[
                "$SERVICE_SG"
            ]
        }
    },
    "healthCheckGracePeriodSeconds": $HEALTH_CHECK,
    "desiredCount":$DESIREDCOUNT
}
EOF
#cat create-service.json
  aws ecs create-service --cli-input-json file://create-service.json --region $AWS_DEFAULT_REGION
else
   echo "*************************************"
   echo "$SERVICE_NAME Sevice exists !!!"
   echo "*************************************"
fi
}

create_ecs_fargate_service_nacos(){
# SERVICE_SUBNETS=`cat /tmp/SERVICE_SUBNETS`
# us_test=`grep subnet-03821f4871c8f92f9 /tmp/SERVICE_SUBNETS|wc -l`
# ap_test=`grep sg-0ee4f7c46c1e4d9bc /tmp/SERVICE_SUBNETS|wc -l`
# us_pro1=`grep subnet-ebd4a28e /tmp/SERVICE_SUBNETS|wc -l`
# us_pro2=`grep subnet-085a3fdf36d5b77c4 /tmp/SERVICE_SUBNETS|wc -l`
if [[ "$ENV" == "dev" ]] || [[ "$ENV" == "test" ]] || [[ "$ENV" == "pda" ]];then
  if [[ "$AWS_DEFAULT_REGION" == "us-east-1" ]];then
    SERVICE_SUBNETS='"subnet-00d52a99cb51b5b2e","subnet-03821f4871c8f92f9"'
    local SERVICE_SG="sg-00995a6fcf5e38697"
    local DESIREDCOUNT=1
  elif [ "$AWS_DEFAULT_REGION" == "ap-southeast-1" ]; then
    local SERVICE_SG="sg-0ee4f7c46c1e4d9bc"
    local DESIREDCOUNT=1
  else
    local SERVICE_SG="$SERVICE_SG"
    local DESIREDCOUNT="$DESIREDCOUNT"
    SERVICE_SUBNETS="$SERVICE_SUBNETS"
  fi
else
  if [[ "$ENV" == "pre" ]] && [[ "$AWS_DEFAULT_REGION" == "us-east-1" ]] || [[ "$ENV" == "pro" ]] && [[ "$AWS_DEFAULT_REGION" == "us-east-1" ]];then
    local SERVICE_SG="sg-dc67b7a3"
    SERVICE_SUBNETS='"subnet-0e2dc546","subnet-638a006f","subnet-787d4a23","subnet-c6f7cfeb","subnet-db0062e7","subnet-ebd4a28e"'
    local DESIREDCOUNT=1
#   elif [[ "$ENV" == "pre" ]] && [[ $us_pro2 -eq 1 ]];then
#     local SERVICE_SG="sg-07883bcc1758fe878"
#     SERVICE_SUBNETS=
#     local DESIREDCOUNT=1
#   elif [[ $us_pro1 -eq 1 ]];then
#     local SERVICE_SG="sg-dc67b7a3"
#     local DESIREDCOUNT=2
#   elif [ $us_pro2 -eq 1 ]; then
#     local SERVICE_SG="sg-07883bcc1758fe878"
#     local DESIREDCOUNT=2
  elif [ "$AWS_DEFAULT_REGION" == "ap-southeast-1" ]; then
    local SERVICE_SG="sg-0ee4f7c46c1e4d9bc"
    local DESIREDCOUNT=2
  else
    local SERVICE_SG="$SERVICE_SG"
    local DESIREDCOUNT="$DESIREDCOUNT"
    SERVICE_SUBNETS="$SERVICE_SUBNETS"
  fi
fi
sum=`aws ecs list-services --cluster $CLUSTER_NAME --region $AWS_DEFAULT_REGION|grep $SERVICE_NAME|awk -F "$CLUSTER_NAME/" '{print$2}'|awk -F "\"" '{print$1}'|wc -l`
if [ $sum -eq 0 ];then
cat >create-service.json<<EOF
{
    "cluster":"$CLUSTER_NAME",
    "serviceName":"$SERVICE_NAME",
    "taskDefinition":"$SERVICE_NAME",
    "launchType":"FARGATE",
    "platformVersion":"1.4.0",
    "networkConfiguration":{
        "awsvpcConfiguration":{
            "assignPublicIp":"ENABLED",
            "subnets":[
                $SERVICE_SUBNETS
            ],
            "securityGroups":[
                "$SERVICE_SG"
            ]
        }
    },
    "desiredCount":$DESIREDCOUNT
}
EOF
  cat create-service.json
  aws ecs create-service --cli-input-json file://create-service.json --region $AWS_DEFAULT_REGION
else
   echo "*************************************"
   echo "$SERVICE_NAME Sevice exists !!!"
   echo "*************************************"
fi
}

conf_dns_alb(){
DNSNAME=`cat /tmp/DNSNAME`
ALB_ID=`cat /tmp/ALB_ID`
DOMAIN=`echo $DOMAIN_NAME|grep -Eo '[^.]+\.[^.]+$'` #提取主域名
local DOMAIN_NAME_ID=`aws route53 list-hosted-zones |grep \"$DOMAIN -B1|head -1|awk -F "\"" '{print$4}'|awk -F "/" '{print$3}'` #托管域名ID
sum=`aws route53 list-resource-record-sets --hosted-zone-id $DOMAIN_NAME_ID --query "ResourceRecordSets[?Name == '${DOMAIN_NAME}.']"|grep $DOMAIN_NAME|wc -l`
if [ $sum -eq 0 ];then
cat >create_dns_alb.json<<EOF
{
     "Comment": "Creating $SERVICE_NAME $DOMAIN_NAME Type A",
     "Changes": [{
                "Action": "CREATE",
                "ResourceRecordSet": {
                            "Name": "$DOMAIN_NAME",
                            "Type": "A",
                            "AliasTarget":{
                                    "HostedZoneId": "$ALB_ID",
                                    "DNSName": "dualstack.$DNSNAME",
                                    "EvaluateTargetHealth": false
                              }}
                          }]
}
EOF

aws route53 change-resource-record-sets --hosted-zone-id $DOMAIN_NAME_ID --change-batch file://create_dns_alb.json
else
  echo "*************************************"
  echo "route53 $DOMAIN_NAME exists !!!"
  echo "*************************************"
fi
}

update_service(){
aws ecs update-service --cluster $CLUSTER_NAME --region $AWS_DEFAULT_REGION --force-new-deployment --service ${SERVICE_NAME} --task-definition ${SERVICE_NAME}
}

send_dingding_image(){
cat >send_info.sh<<EOF
curl "https://oapi.dingtalk.com/robot/send?access_token=$DINGDING_TOKEN" -H 'Content-Type: application/json' -d ' {"msgtype": "markdown","markdown": {"title":"Deploy Upload image succeeded, please restart $SERVICE_NAME","text": "<font face='黑体' color='#00EC00'>Deploy $SERVICE_NAME Success</font>\n"}}'
EOF

bash send_info.sh
}

send_dingding(){
cat >send_info.sh<<EOF
curl "https://oapi.dingtalk.com/robot/send?access_token=$DINGDING_TOKEN" -H 'Content-Type: application/json' -d ' {"msgtype": "markdown","markdown": {"title":"Deploy $SERVICE_NAME Success","text": "<font face='黑体' color='#00EC00'>Deploy $SERVICE_NAME Success</font>\n"}}'
EOF

bash send_info.sh
}

auto_scaling_cpu(){
aws application-autoscaling register-scalable-target \
    --service-namespace ecs \
    --scalable-dimension ecs:service:DesiredCount \
    --resource-id service/$CLUSTER_NAME/${SERVICE_NAME} \
    --min-capacity $DESIREDCOUNT \
    --max-capacity 5 \
    --region $AWS_DEFAULT_REGION

cat >cpu90-target-tracking-scaling-policy-config.json<<EOF
{
     "TargetValue": 90.0,
     "PredefinedMetricSpecification": {
         "PredefinedMetricType": "ECSServiceAverageCPUUtilization"
     },
     "ScaleOutCooldown": 60,
    "ScaleInCooldown": 60
}
EOF

aws application-autoscaling put-scaling-policy \
--service-namespace ecs \
--scalable-dimension ecs:service:DesiredCount \
--resource-id service/$CLUSTER_NAME/${SERVICE_NAME} \
--policy-name cpu90-target-tracking-scaling-policy \
--policy-type TargetTrackingScaling \
--target-tracking-scaling-policy-configuration file://cpu90-target-tracking-scaling-policy-config.json \
--region $AWS_DEFAULT_REGION
}

auto_scaling_mem(){
aws application-autoscaling register-scalable-target \
    --service-namespace ecs \
    --scalable-dimension ecs:service:DesiredCount \
    --resource-id service/$CLUSTER_NAME/${SERVICE_NAME} \
    --min-capacity $DESIREDCOUNT \
    --max-capacity 5 \
    --region $AWS_DEFAULT_REGION

cat >mem90-target-tracking-scaling-policy-config.json<<EOF
{
     "TargetValue": 90.0,
     "PredefinedMetricSpecification": {
         "PredefinedMetricType": "ECSServiceAverageMemoryUtilization"
     },
     "ScaleOutCooldown": 60,
    "ScaleInCooldown": 60
}
EOF

aws application-autoscaling put-scaling-policy \
--service-namespace ecs \
--scalable-dimension ecs:service:DesiredCount \
--resource-id service/$CLUSTER_NAME/${SERVICE_NAME} \
--policy-name mem90-target-tracking-scaling-policy \
--policy-type TargetTrackingScaling \
--target-tracking-scaling-policy-configuration file://mem90-target-tracking-scaling-policy-config.json \
--region $AWS_DEFAULT_REGION
}

create_cloudfront(){
sum=`aws cloudfront list-distributions --region $AWS_DEFAULT_REGION|grep CNAME|grep ${CDN_DOMAIN_NAME}|wc -l`
if [ $sum -eq 0 ];then
 cat >create_cloudfront.json<<EOF
{
    "CallerReference": "cli-deploy-${SERVICE_NAME}",
    "Aliases": {
        "Quantity": 1,
        "Items": [
                "${CDN_DOMAIN_NAME}"
            ]
    },
    "DefaultRootObject": "index.html",
    "Origins": {
        "Quantity": 1,
        "Items": [
            {
                "Id": "${BUCKET_NAME}",
                "DomainName": "${BUCKET_NAME}.s3.amazonaws.com",
                "OriginPath": "/${ENV}/${SERVICE_NAME}",
                "CustomHeaders": {
                    "Quantity": 0
                },
                "S3OriginConfig": {
                    "OriginAccessIdentity": ""
                },
                    "ConnectionAttempts": 3,
                    "ConnectionTimeout": 10,
                    "OriginShield": {
                        "Enabled": false
                    }
            }
        ]
    },
    "OriginGroups": {
        "Quantity": 0
    },
    "DefaultCacheBehavior": {
        "TargetOriginId": "${BUCKET_NAME}",
        "ForwardedValues": {
            "QueryString": false,
            "Cookies": {
                "Forward": "none"
            },
            "Headers": {
                "Quantity": 0
            },
            "QueryStringCacheKeys": {
                "Quantity": 0
            }
        },
        "TrustedSigners": {
            "Enabled": false,
            "Quantity": 0
        },
        "ViewerProtocolPolicy": "redirect-to-https",
        "MinTTL": 0,
        "AllowedMethods": {
            "Quantity": 2,
            "Items": [
                "HEAD",
                "GET"
            ],
            "CachedMethods": {
                "Quantity": 2,
                "Items": [
                    "HEAD",
                    "GET"
                ]
            }
        },
        "SmoothStreaming": false,
        "DefaultTTL": 300,
        "MaxTTL": 300,
        "Compress": true,
        "LambdaFunctionAssociations": {
            "Quantity": 0
        },
            "FunctionAssociations": {
                "Quantity": 0
            },
        "FieldLevelEncryptionId": ""
    },
    "CacheBehaviors": {
        "Quantity": 0
    },
    "CustomErrorResponses": {
        "Quantity": 2,
        "Items": [
                {
                    "ErrorCode": 403,
                    "ResponsePagePath": "/index.html",
                    "ResponseCode": "200",
                    "ErrorCachingMinTTL": 300
                },
                {
                    "ErrorCode": 404,
                    "ResponsePagePath": "/index.html",
                    "ResponseCode": "200",
                    "ErrorCachingMinTTL": 300
                }
            ]
    },
    "Comment": "",
    "Logging": {
        "Enabled": false,
        "IncludeCookies": false,
        "Bucket": "",
        "Prefix": ""
    },
    "PriceClass": "PriceClass_All",
    "Enabled": true,
    "ViewerCertificate": {
        "ACMCertificateArn": "arn:aws:acm:${AWS_DEFAULT_REGION}:${AWS_ACCOUNT_ID}:certificate/${CERTIFICATE_ID}",
            "SSLSupportMethod": "sni-only",
        "MinimumProtocolVersion": "TLSv1.2_2021",
        "Certificate": "arn:aws:acm:${AWS_DEFAULT_REGION}:${AWS_ACCOUNT_ID}:certificate/${CERTIFICATE_ID}",
        "CertificateSource": "acm"
    },
    "Restrictions": {
        "GeoRestriction": {
            "RestrictionType": "none",
            "Quantity": 0
        }
    },
    "WebACLId": "",
    "HttpVersion": "http2",
    "IsIPV6Enabled": true
}
EOF

aws cloudfront create-distribution --distribution-config file://create_cloudfront.json --region $AWS_DEFAULT_REGION|grep DomainName|head -1 |awk -F "\"" '{print$4}' >/tmp/CDN_AUTO_DOMAINNAME
else
   echo "*************************************"
   echo "cloudfront ${SERVICE_NAME} exists !!!"
   echo "*************************************"
fi
}

conf_dns_cloudfront(){
sum=`aws route53 list-resource-record-sets --hosted-zone-id $DOMAIN_NAME_ID --query "ResourceRecordSets[?Name == '${CDN_DOMAIN_NAME}.']"|grep $CDN_DOMAIN_NAME|wc -l`
if [ $sum -eq 0 ];then
CDN_AUTO_DOMAINNAME=`cat /tmp/CDN_AUTO_DOMAINNAME`
cat >conf_dns_cloudfront.json<<EOF
{
     "Comment": "Creating $SERVICE_NAME $CDN_DOMAIN_NAME Type A",
     "Changes": [{
                "Action": "CREATE",
                "ResourceRecordSet": {
                            "Name": "$CDN_DOMAIN_NAME",
                            "Type": "A",
                            "AliasTarget":{
                                    "HostedZoneId": "Z2FDTNDATAQYW2",
                                    "DNSName": "${CDN_AUTO_DOMAINNAME}",
                                    "EvaluateTargetHealth": false
                              }}
                          }]
}
EOF

aws route53 change-resource-record-sets --hosted-zone-id $DOMAIN_NAME_ID --change-batch file://conf_dns_cloudfront.json
else
  echo "*************************************"
  echo "route53 $CDN_DOMAIN_NAME exists !!!"
  echo "*************************************"
fi
}

case $1 in
"create_servicediscovery")
create_servicediscovery;;
"create_ecr_repository")
create_ecr_repository;;
"create_ecs_fargate_task")
create_ecs_fargate_task;;
"update_ecs_fargate_task_jvm")
update_ecs_fargate_task_jvm;;
"conf_alb")
conf_alb;;
"conf_alb_http")
conf_alb_http;;
"conf_alb_https")
conf_alb_https;;
"conf_alb_grpc")
conf_alb_grpc;;
"create_ecs_fargate_grpc_service")
create_ecs_fargate_grpc_service;;
"create_ecs_fargate_service")
create_ecs_fargate_service;;
"create_ecs_fargate_service_nacos")
create_ecs_fargate_service_nacos;;
"conf_dns_alb")
conf_dns_alb;;
"send_dingding_image")
send_dingding_image;;
"update_service")
update_service;;
"send_dingding")
send_dingding;;
"auto_scaling_cpu")
auto_scaling_cpu;;
"auto_scaling_mem")
auto_scaling_mem;;
"create_cloudfront")
create_cloudfront;;
"conf_dns_cloudfront")
conf_dns_cloudfront;;
*)
echo "This feature is not developed"
exit 1
;;
esac
