state(state_2,no_motion).
state(marquesina,no_motion).
state(kitchen,no_motion).
state(room_1,motion).
state(dining_room,no_motion).
state(principal_room,motion).
state(backyard,no_motion).
state(living_room,no_motion).
state(room_2,motion).
state(wdinning2,locked).
state(wdinning1,locked).
state(wkitchen1,locked).
state(wprincipal3,locked).
state(wprincipal2,locked).
state(wprincipal1,locked).
state(wroom_3,locked).
state(wroom_2,locked).
state(wroom_1,locked).
state(wliving2,locked).
state(wliving1,locked).
state(doorPB,open).
state(principal1,open).
state(doorLB,open).
state(living1,open).
state(room_2D,open).
state(room_1D,open).
state(room_3D,open).
state(living2D,open).
state(doorR2B,open).
state(fridge,off).
state(living2L,off).
state(room_1L,off).
state(dryer,off).
state(pc,on).
state(washing_machine,on).
state(dish_washer,on).
state(microwave,on).
state(doorGL,closed).
state(doorGK,closed).
state(principal2,closed).

room(room_2,16.0,on).
room(marquesina,25.0,off).
room(backyard,29.0,off).
room(living_room,20.0,off).
room(kitchen,25.0,off).
room(dining_room,25.0,off).
room(principal_room,16.0,on).
room(room_1,20.0,on).

currenttime(0,40,22,6).
currentseason(summer,29).

family(elainer,principal_room,awake).
family(eduardo,room_1,awake).
family(djidjelly,room_2,awake).
family(ernesto,out,awake).


solar(panel3,night,0.0).
solar(panel2,night,0.0).
solar(panel1,night,0.0).

house(normal).
