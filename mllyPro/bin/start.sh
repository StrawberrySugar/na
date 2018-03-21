#!/bin/sh

CURRENT_DIR=`dirname  $0`
run_cmd="$CURRENT_DIR/api.sh "$*
exec $run_cmd &