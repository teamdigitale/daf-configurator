#!/usr/bin/env bash

set -e

# deployEnv=$1

kubectl --kubeconfig=$KUBECONFIG delete configmap daf-configurator-conf || true
kubectl --kubeconfig=$KUBECONFIG create configmap daf-configurator-conf --from-file=../conf/${DEPLOY_ENV}/prodBase.conf
kubectl --kubeconfig=$KUBECONFIG replace -f daf_configurator_logback.yml --force
kubectl --kubeconfig=$KUBECONFIG replace -f daf_configurator_${DEPLOY_ENV}.yaml --force
