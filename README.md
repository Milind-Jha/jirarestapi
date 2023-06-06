# jirarestapi


Endpoints -

Get Mapping-

http://localhost:8080/jira/users/                         [get all users]
http://localhost:8080/jira/users/{userId}                 [getUser]
http://localhost:8080/jira/ticket/assignedticket/{userId} [get all assigned ticket for user with user id]
http://localhost:8080/jira/ticket/createdticket/{userId}  [get all created ticket for user with user id]
http://localhost:8080/jira/ticket/allticket/{userId}      [get all created and assigned ticket for user with userid]

Post Mapping-

http://localhost:8080/jira/users/                         [create user] {@RequestBody User user}

http://localhost:8080/jira/ticket/createTicket/{userId}   [create ticket by user of user id] { @RequestBody Ticket ticket}

Put Mapping-
http://localhost:8080/jira/users/updateuser/{userId}      [update user with user id] {@RequestBody User user}

Patch Mapping-
http://localhost:8080/jira/ticket/updatestatus/{userId}/{ticketId} [update status for ticket with ticket id by user id] {@RequestBody String finalStatus}
http://localhost:8080/jira/ticket/cancelticket/{userId}/{ticketId} [cancel ticket for ticket id and cancelled by user with user id]
http://localhost:8080/jira/ticket//updateassigned/{userId}/{newUserId}/{ticketId} [update ticket assigned to from user id to new user id]

Delete Mapping - 
http://localhost:8080/jira/users/{userId}  [delete user with user id]
