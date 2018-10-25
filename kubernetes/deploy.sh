#!/usr/bin/env bash

set -e

# deployEnv=$1

<<<<<<< HEAD
kubectl --kubeconfig=$KUBECONFIG delete configmap daf-configurator-conf || true
kubectl --kubeconfig=$KUBECONFIG create configmap daf-configurator-conf --from-file=../conf/${DEPLOY_ENV}/prodBase.conf
kubectl --kubeconfig=$KUBECONFIG replace -f daf_configurator_logback.yml --force
kubectl --kubeconfig=$KUBECONFIG replace -f daf_configurator_${DEPLOY_ENV}.yaml --force
=======
kubectl --kubeconfig=$KUBECONFIG delete configmap daf-configurator-conf -n testci || true
kubectl --kubeconfig=$KUBECONFIG create configmap daf-configurator-conf -n testci --from-file=../conf/${DEPLOY_ENV}/prodBase.conf
kubectl --kubeconfig=$KUBECONFIG replace -f daf_configurator_logback.yml -n testci --force
kubectl --kubeconfig=$KUBECONFIG replace -f daf_configurator_${DEPLOY_ENV}.yaml -n testci --force
>>>>>>> b8b578f564c6420923b7b73848509d7e88788dda
