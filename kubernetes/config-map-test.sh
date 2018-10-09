#!/usr/bin/env bash
kubectl delete configmap daf-configurator-conf || true
kubectl create configmap daf-configurator-conf --from-file=../conf/test/prodBase.conf