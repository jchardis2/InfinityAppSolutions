SELECT distinct `org_users`. * , `org_admin`.`adduser` ,  `org_admin`.`addadmin` ,  `org_admin`.`editAllProjects` 
FROM  `org_users` ,  `org_admin` 
WHERE (
(
 `org_admin`.`userid` =1
)
AND (
 `org_users`.`userid` =1
)
)
LIMIT 0 , 30