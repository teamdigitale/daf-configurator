#!/usr/bin/env bash

set -e

# deployEnv=$1

kubectl --kubeconfig=$KUBECONFIG delete configmap daf-configurator-conf -n testci || true
kubectl --kubeconfig=$KUBECONFIG create configmap daf-configurator-conf -n testci --from-file=../conf/${DEPLOY_ENV}/prodBase.conf
kubectl --kubeconfig=$KUBECONFIG replace -f daf_configurator_logback.yml -n testci --force
kubectl --kubeconfig=$KUBECONFIG replace -f daf_configurator_${DEPLOY_ENV}.yaml -n testci --force
