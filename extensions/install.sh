#!/bin/sh

injected_dir=$1

source /usr/local/s2i/install-common.sh

install_deployments ${injected_dir}/ocp-app-0.1.0-SNAPSHOT.war

install_modules ${injected_dir}/modules

configure_drivers ${injected_dir}/drivers.env
