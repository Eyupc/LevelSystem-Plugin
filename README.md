# LevelSystem-Plugin
## Usage
- Import the sql file in your database.
- Change the values in emulator_settings
"habron.ws.host" to your websocket host. 
"habron.ws.port" to your websocket port. 
### Adding levels
There is a table called 'habron_levelsystem_levels', you can add here levels as much as u want.<br/>
It has to be in chronological order.<br/>
For example:

Level | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11
--- | --- | --- | --- |--- |--- |--- |--- |--- |--- |--- |---
XP | 100 | 250 | 500 | 1000 | 2000 | 4000 | 5000 | 7500 | 8500 | 9900 | 10000

So if the user reached 100XP, he will get a level-up (reaches level 2). After that he has to have 250XP to get a level-up and reach level 3.
  
  
### Adding tasks
An example of adding a task. <br/>
Type = always 'e'. <br/>
RoomId = Room where the event takes place. <br/>
Item_id = When a user has the task done, he has to walk on a furniture.<br/>
This id has to be the id of that furni.<br/>

id | name | desc |image | type | roomId | XP | item_id | enabled
--- | --- | --- | --- |--- |--- |--- |--- |---
1 | Find the furni | {Description}  | https://imageurl.com/img.png | e | {roomId} | amount of XP | {itemId} | {0/1} (boolean)

### **_NOTE:_**
- NEVER DELETE A TASK, WHEN A USER HAS DONE IT! 
- NEVER CHANGE THE ID OF A TASK!
- SET ENABLED TO 0 IF YOU WANT TO HIDE THE TASK!

## Commands
- update_levels
- update_tasks 
You can update this data in game without restarting the emulator.


