TRIGGER
-------------
it is very similar to procedure , difference is procedure is executed by the programmer , where as TRIGGER
will automatically execute.

syntax:

create or replace trigger trigger_name
before/after
insert or update or delete
on table_name
begin
 if inserting
 then
 do_something;
 end if;
 if deleting
 then
 do_something;
 end if;
 if updating
 then
 do_something;
 end if;
end trigger_name;
/

Note:
-------------------
-> select trigger_name from user_triggers; //it will display all the triggers present in oracle server
->drop trigger trigger_name; //delete a trigger

Examples.
-----------------
1.//create a trigger that restricts drop operation
 
 create or replace trigger trig_restrict_drop
 before
 drop or create 
 on system.schema
 begin
 raise_application_error(-20001,'Opertaion denied by user sambit');
 end trig_restrict_drop;
 /
2.//create a trigger that gives a message after every dml operation

 create or replace trigger trig_msg_after_dml
 after
 insert or update  or delete
 on student
 begin
 if inserting
 	then dbms_output.put_line('Record insert heigala re');
 end if;
 if updating
 	then dbms_output.put_line('Record update heigala re');
 end if;
 if deleting
 	then dbms_output.put_line('Record delete heigala ree');
 end if;
 end trig_msg_after_dml;
 /
