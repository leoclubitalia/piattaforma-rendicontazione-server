#!/bin/bash

#variablesd
db_name=""
db_user=""
db_password=""
encryption_password=""
bkp_host=""
bkp_user=""
bkp_password=""

#backup
today=$(date +"%Y_%m_%d:%H_%M_%S")
file_path="db_$today.sql"
mysqldump -u $db_user -p $db_password $db_name > $file_path

#encrypting
file_path_encoded=$file_path".enc"
openssl enc -in $file_path -aes-256-cbc -pass stdin > $file_path_encoded <<ENCRYPTING
$encryption_password
ENCRYPTING

rm $file_path

#upload
HOST=$bkp_host
USER=$bkp_user
PASSWD=$bkp_password
FILE=$file_path_encoded
UPLOAD_DIRECTORY="uploaded"
ftp -n $HOST <<END_SCRIPT
quote USER $USER
quote PASS $PASSWD
cd $UPLOAD_DIRECTORY
binary
put $FILE
quit
END_SCRIPT
exit 0
