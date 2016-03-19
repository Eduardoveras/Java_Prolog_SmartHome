:- dynamic state/2.      %para house, appliance,  door, window, electronic, lights, sensor%

:- dynamic room/3.       %room, temperature, light state%

:- dynamic house/1.	 %mode%

:- dynamic currentseason/2. %season, temperature%

:- dynamic currenttime/4. %time,day,month----------------solar panels%

:- dynamic family/3.     %member, location, state%

:- dynamic guest/2.	 %guest, location%

:- dynamic solar/3.      %panel, angle, output%

:- dynamic special/2.	 %device, location%


                    %Hecho%
%Appliance%
appliance(fridge, kitchen, 4).
appliance(dish_washer, kitchen, 2).
appliance(microwave, kitchen, 1).
appliance(smartTV, room1, 5).
appliance(casinito, princ_room, 5).
appliance(washing_machine, garage, 2).
appliance(dryier, garage, 3).
%Appliance%

%Turning appliance on off opened closed%

applianceSwitch(App) :-
	state(App, off),
	retractall(state(App, off)),
	asserta(state(App, on)).
applianceSwitch(App) :-
	state(App, on),
	retractall(state(App, on)),
	asserta(state(App, off)).

applianceSwitch(App) :-
	state(App, open),
	retractall(state(App, open)),
	asserta(state(App, closed)).
applianceSwitch(App) :-
	state(App, closed),
	retractall(state(App, closed)),
	asserta(state(App, open)).
	
%Turning appliance on off opened closed%

%Door%
door(doorLB, living_room, backyard).
door(doorR2B, room2, backyard).
door(doorPB, principal_room, backyard).

door(doorGK, garage, kitchen).
%Door%

%Windows%
window(wliving1, living_room).
window(wliving2, living_room).

window(wroom_1, room1).

window(wroom_2, room2).
window(wroom_3, room2).

window(wprincipal1, principal_room).
window(wprincipal2, principal_room).
window(wprincipal3, principal_room).

window(wkitchen1, kitchen).

window(wdinning1, dinning_room).
window(wdinning2, dinning_room).
%Windows%


%Electronics%
electronic(flatscreen1, living_room, 0.1).
electronic(dvd_player, living_room, 0.1).
electronic(sound_system, living_room, 0.3).
electronic(radio1, living_room, 0.2).
electronic(lamp1, living_room, 0.1).
electronic(lamp2, living_room, 0.1).
electronic(computer, living_room, 0.4).

electronic(flatscreen2, room1, 0.1).
electronic(ps4, room1, 0.4).
electronic(radio2, room1, 0.2).
electronic(lamp3, room1,0.1).

electronic(tv, room2, 0.3).
electronic(radio3, room2, 0.2).
electronic(lamp4, room2, 0.2).

electronic(flatscreen3, principal_room, 0.1).
electronic(radio4, principal_room, 0.1).
electronic(lamp5, principal_room, 0.1).
electronic(lamp6, principal_room,0.1).

electronic(blender, kitchen, 0.2).
%Electronics%


%Sensor%

%Sensor%


%Temporadas%
season(spring).
season(summer).
season(autumn).
season(winter).

%Temporadas in Idaho%

%Ciclo del dia%
sunangle(spring, -91.66, [6,43], [7,00]).
sunangle(spring, -87.41, [7,1], [11,59]).
sunangle(spring, -12.41, [12,00], [12,59]).
sunangle(spring, 2.59, [13,00], [20,23]).
sunangle(spring, 260, [20,24], [23,59]).
sunangle(spring, 260, [0,0], [6,42]).

sunangle(summer, -74.98, [7,46], [8,00]).
sunangle(summer, -71.23, [8,1], [11,59]).
sunangle(summer, -11.48, [12,00], [12,59]).
sunangle(summer, 3.52, [13,00], [20,25]).
sunangle(summer, 260, [20,26], [23,59]).
sunangle(summer, 260, [0,0], [7,45]).

sunangle(autumn, -96.54, [5,59], [6,0]).
sunangle(autumn, -96.04, [6,1], [11,59]).
sunangle(autumn, -6.29, [12,00], [12,59]).
sunangle(autumn, 8.71, [13,00], [19,48]).
sunangle(autumn, 260, [18,51], [23,59]).
sunangle(autumn, 260, [0,0], [5,58]).

sunangle(winter, -105.16, [5,47], [6,0]).
sunangle(winter, -90.16, [6,1], [11,59]).
sunangle(winter, -12.16, [12,00], [12,59]).
sunangle(winter, 2.84, [13,00], [19,46]).
sunangle(winter, 260, [19,47], [23,59]).
sunangle(winter, 260, [0,0], [9,11]).
%Ciclo del dia%

%Especial%
special(garage_door, garaje).

special(alarma, salon).
%Especial%


                    %Hecho%

		    %Auxiliar%
timecheck(Hour, _) :- (Hour > 24; Hour < 0), !, fail.
timecheck(_, Minute) :- (Minute > 59; Minute < 0), !,fail.
timecheck(_, _).

lightConsumption([], 0) :- !.
lightConsumption([Room | Rooms], Output) :-
	room(Room, _, _),
	lightConsumption(Rooms, Sum),
	Output is 0.1 + Sum.

deviceConsumption([], 0) :- !.
deviceConsumption([Device | Devices], Output) :-
	electronic(Device, _, Consumption),
	deviceConsumption(Devices, Sum),
	Output is Consumption + Sum.
                    %Auxiliar%


		    %Reglas%

%Locking Security Protocol%
unlockedwindows(Windows) :-
	findall(Window, (state(Window, unlocked), window(Window, _)), Windows).

windowLock(Window) :-
	window(Window, _),
	retractall(state(Window, unlocked)),
	asserta(state(Window, locked)).

windowsLock([]) :- !.
windowsLock([Window | Windows]) :-
	window(Window, _),
	state(Window, locked),
	windowsLock(Windows).
windowsLock([Window | Windows]) :-
	windowLock(Window),
	windowsLock(Windows).

allWindowsLock :-
	unlockedwindows(Windows),
	windowsLock(Windows).

unlockeddoor(Doors) :-
	findall(Door, (state(Door, unlocked), door(Door, _, _)), Doors).

doorLock(Door) :-
	door(Door, _, _),
	retractall(state(Door, _)),
	asserta(state(Door, locked)).

doorsLock([]) :- !.
doorsLock([Door | Doors]) :-
	state(Door, locked),
	doorsLock(Doors).
doorsLock([Door | Doors]) :-
	doorLock(Door),
	doorsLock(Doors).

allDoorsLock :-
	unlockeddoor(Doors),
	doorsLock(Doors).
%Locking Security Features%



%Unlocking Security Feature%
lockedwindows(Windows) :-
	findall(Window, (state(Window, locked), window(Window,	_)), Windows).

windowUnlock(Window) :-
	window(Window,_),
	retractall(state(Window, locked)),
	asserta(state(Window, unlocked)).

windowsUnlock([]) :- !.
windowsUnlock([Window | Windows]) :-
	window(Window,_),
	state(Window, unlocked),
	windowsUnlock(Windows).
windowsUnlock([Window | Windows]) :-
	windowUnlock(Window),
	windowsUnlock(Windows).

allWindowUnlock :-
	lockedwindows(Windows),
	windowsUnlock(Windows).

lockeddoor(Doors) :-
	findall(Door, (state(Door, locked), door(Door, _, _)), Doors).

doorUnlock(Door) :-
	door(Door, _, _),
	retractall(state(Door, _)),
	asserta(state(Door, unlocked)).

doorsUnlock([]) :- !.
doorsUnlock([Door | Doors]) :-
	door(Door, _, _),
	state(Door, unlocked),
	doorsUnlock(Doors).
doorsUnlock([Door | Doors]) :-
	doorUnlock(Door),
	doorsUnlock(Doors).

allDoorsUnlock :-
	lockeddoor(Doors),
	doorsUnlock(Doors).
%Unlocking Security Feature%



%Home Shutdown Protocol%
whatson(Devices) :-
	findall(Device, (state(Device, on), electronic(Device, _, _)), Devices).

turnOff(Device) :-
	electronic(Device, _, _),
	retractall(state(Device, on)),
	asserta(state(Device, off)).

turnsOff([]) :- !.
turnsOff([Device | Devices]) :-
	electronic(Device, _, _),
	state(Device, off),
	turnsOff(Devices).
turnsOff([Device | Devices]) :-
	turnOff(Device),
	turnsOff(Devices).


everythingOff :-
	whatson(Devices),
	turnsOff(Devices).
%Home Shutdown Protocol%







%Home Booting Feature%
whatsoff(Devices) :-
	findall(Device, (state(Device, off), electronic(Device, _, _)), Devices).

turnOn(Device) :-
	electronic(Device, _, _),
	retract(state(Device, off)),
	asserta(state(Device, on)).

turnsOn([]) :- !.
turnsOn([Device | Devices]) :-
	electronic(Device, _, _),
	state(Device, on),
	turnsOn(Devices).
turnsOn([Device | Devices]) :-
	turnOn(Device),
	turnsOn(Devices).

everythingOn :-
	whatsoff(Devices),
	turnsOn(Devices).
%Home Booting Feature%


%Light Shutdown Protocol%
lighton(Rooms) :-
	findall(Room, room(Room, _, on), Rooms).

turnLightOff(Room) :-
	room(Room,Temperature, _),
	retractall(room(Room,_, _)),
	asserta(room(Room, Temperature, off)).

turnLightsOff([]) :- !.
turnLightsOff([Room | Rooms]) :-
	room(Room, _, off),
	turnLightsOff(Rooms).
turnLightsOff([Room | Rooms]) :-
	turnLightOff(Room),
	turnLightsOff(Rooms).

allOff :-
	lighton(Rooms),
	turnLightsOff(Rooms).
%Light Shutdown Protocol%



%Manual Lighting Manegement Feature%

lightoff(Rooms) :-
	findall(Room, room(Room, _, off), Rooms).

turnLightOn(Room):-
	room(Room, Temperature, _),
	retractall(room(Room, Temperature, _)),
	asserta(room(Room, Temperature, on)).

turnLightsOn([]) :- !.
turnLightsOn([Room | Rooms]) :-
	room(Room, _, on),
	turnLightsOn(Rooms).
turnLightsOn([Room | Rooms]) :-
	turnLightOn(Room),
	turnLightsOn(Rooms).

allOn :-
	lightoff(Lights),
	turnLightsOn(Lights).
%Manual Lighting Manegement Feature%




%Solar Panel Monitoring system%
calculateAngle(night) :-
	currenttime(Hour, Minute, _, _),
	currentseason(Season, _),
	sunangle(Season, 260, [IHour | IMinute], [FHour | FMinute]),
	Hour >= IHour,
	Minute >= IMinute,
	Hour =< FHour,
	Minute =< FMinute, !.
calculateAngle(Angle) :-
	currenttime(Hour,  Minute, _, _),
	currentseason(Season, _),
	sunangle(Season, A, [IHour | IMinute], [FHour | FMinute]),
	Hour >= IHour,
	Minute >= IMinute,
	Hour =< FHour,
	Minute =< FMinute,
	DeltaH is (Hour - IHour) * 60,
	DeltaM is Minute- IMinute,
	Angle is A + (0.25 * (DeltaH + DeltaM)), !.

sunoutput(0) :-
	calculateAngle(Angle),
	not(number(Angle)), !.
sunoutput(Output) :-
	calculateAngle(Angle),
	Output is  640* abs(cos(Angle)).


output(Output) :-
	sunoutput(SO),
	Output is 0.99 * (SO * 4).

setOutput(_):-
	output(Output),
	calculateAngle(Angle),
	panels(Panels),
	member(P, Panels),
	retractall(solar(P, _, _)),
	asserta(solar(P, Angle, Output)).
%Solar Panel Monitoring system%



%Energy Consumption Monitoring Feature%

panels(Panels) :-
	findall(Panel, solar(Panel, _, _), Panels).

solarCharge([], 0) :- !.
solarCharge([Panel | Panels], Output) :-
	solar(Panel, _, Charge),
	solarCharge(Panels, Sum),
	Output is Charge + Sum.
	
applianceConsumption([], 0) :- !.
applianceConsumption([Appliance | Appliances], Output) :- 
	appliance(Appliance, _, Consumption),
	applianceConsumption(Appliances, Sum),
	Output is Consumption + Sum.

applianceOn(Appliances) :-
	findall(Appliance, (state(Appliance, on), appliance(Appliance, _, _)), Appliances).


energyConsumption(Consumption) :-
	lighton(Rooms),
	lightConsumption(Rooms, C2),
	applianceOn(Appliances),
	applianceConsumption(Appliances, C3),
	Consumption is  (C2 + C3).

rate(Input, Output, danger) :- Input < Output, !.
rate(Input, Output, alert) :- Input = Output, !.
rate(Input, Output, critical) :-  Input = (Output + 1000), !.
rate(_, _, optimal).

consumptionRate(Rate) :-
	panels(Panels),
	solarCharge(Panels, Charge),
	energyConsumption(Consumption),
	rate(Charge, Consumption, Rate).
%Energy Consumption Monitoring Feature%


%Turning appliance on off opened closed%

switchL(L) :-
room(L,T,on),
	retractall(room(L,_,_)),
	asserta(room(L,T,off)),!,fail.
switchL(L) :-
room(L,T,off),
	retractall(room(L,_,_)),
	asserta(room(L,T,on)).
	
%Turning appliance on off opened closed%


%Family Tracker%
home(Members) :-
	findall(Member, (family(Member, Location, _), Location \= out), Members).

awake(Members) :-
	findall(Member, family(Member, _, awake), Members).

occupied(Rooms) :-
	findall(F, (family(_, F, _), F \= out), R1),
	findall(G, (guest(_, G), G \= out), R2),
	append(R1, R2, Rooms).

occupiedBy(Room, Members) :-
	findall(F, family(F, Room, _), M1),
	findall(G, family(G, Room, _), M2),
	append(M1, M2, Members).
	
occupiedByxx(Room, Members) :-
	findall(F, family(F, Room, awake), M1),
	findall(G, family(G, Room, awake), M2),
	append(M1, M2, Members).
%Family Tracker%

%Protocols%
sleep :-
	awake(Members),
	Members = [],
	allOff,
	everythingOff,
	allWindowsLock,
	allDoorsLock.

away :- home(Members),
	Members = [],
	allOff,
	everythingOff,
	allWindowsLock,
	allDoorsLock.

normal :- home(Members),
	Members \= [].

membersRoom(son, room1) :- !.
membersRoom(daugther, room2) :- !.
membersRoom(_, principal_room).

familySleep([]):- !.
familySleep([Member | Members]) :-
	family(Member, _, asleep),
	familySleep(Members).
familySleep([Member | Members]) :-
	membersRoom(Member, Room),
	retractall(family(Member, _, _)),
	asserta(family(Member, Room, asleep)),
	familySleep(Members).
	
wakeUp(Person) :- 
	family(Person, Location, _),
	room(Location, _, _),
	retractall(family(Person,_, _)),
	asserta(family(Person, Location, awake)).

wakeFamily([]) :- !.
wakeFamily([Member | Members]) :-
	family(Member, _, awake),
	wakeFamily(Members).
wakeFamily([Member | Members]) :-
	wakeUp(Member),
	wakeFamily(Members).

familyOut([]) :- !.
familyOut([Member | Members]) :-
	family(Member, out, _),
	familyOut(Members).
familyOut([Member | Members]) :-
	retractall(family(Member, _, _)),
	asserta(family(Member, out, awake)),
	familyOut(Members).

returnFamily([]) :- !.
returnFamily([Member | Members]) :-
	family(Member, Location, _),
	Location \= out,
	returnFamily(Members).
returnFamily([Member | Members]) :-
	retractall(family(Member, _, _)),
	asserta(family(Member, garage, awake)),
	returnFamily(Members).

guestOut([]) :- !.
guestOut([Guest | Guests]) :-
	guest(Guest, out),
	guestOut(Guests).
guestOut([Guest | Guests]) :-
	retractall(guest(Guest, _)),
	asserta(guest(Guest, out)),
	guestOut(Guests).

switchAS(Member):-
	family(Member,L,awake),
	retractall(family(Member,L,awake)),
	asserta(family(Member,L,asleep)),!,fail.
switchAS(Member):-
	family(Member,L,asleep),
	retractall(family(Member,L,_)),
	asserta(family(Member,L,awake)).

manualModeChange(Current, asleep):-
	Current \=  asleep,
	findall(Guest,
		(guest(Guest, Location), Location \= out),
		Guests),
	awake(Members),
	familySleep(Members),
	guestOut(Guests),
	retractall(house(_)),
	asserta(house(asleep)).

manualModeChange(Current, away) :-
	Current \= away,
	findall(Guest,
		(guest(Guest, Location), Location \= out),
		Guests),
	home(Members),
	familyOut(Members),
	guestOut(Guests),
	retractall(house(_)),
	asserta(house(away)).


manualModeChange(asleep, normal) :-
	findall(Member, family(Member, _, _), Members),
	wakeFamily(Members),
	retractall(house(_)),
	asserta(house(normal)).
	
manualModeChange(away, normal) :-
	findall(Member, family(Member, _, _), Members),
	returnFamily(Members),
	retractall(house(_)),
	asserta(house(normal)).

roomWhatsOn(Room, Devices):-
	findall(Device, (state(Device, on), electronic(Device, Room, _)), Devices).

turnRoomOff(Room):-
	roomWhatsOn(Room, Devices),
	turnsOff(Devices).

allRooms(Rooms) :- findall(Room, room(Room, _, _), Rooms).

closeCandidate([],[]) :- !.
closeCandidate([Candidate | Candidates], [Candidate |Rooms]):-
	occupiedBy(Candidate, Members),
	Members = [],
	closeCandidate(Candidates, Rooms), !.
closeCandidate([_ | Candidates], Rooms) :-
	closeCandidate(Candidates, Rooms).

closex(_):-
	allRooms(Rooms),
	closeCandidate(Rooms, R),
	turnRoomOff(R),
	turnLightsOff(R).


openCandidate([], []) :- !.
openCandidate([Candidate | Candidates], [Candidate | Rooms]) :-
	occupiedByxx(Candidate, Members),
	Members \= [],
	openCandidate(Candidates, Rooms).
openCandidate([_ | Candidates], Rooms) :-
	openCandidate(Candidates, Rooms).

open(_) :-
	allRooms(Rooms),
	openCandidate(Rooms, R),
	turnLightsOn(R).


%Protocols%

myRoom(elainer, principal_room) :- !.
myRoom(ernesto, principal_room) :- !.
myRoom(eduardo, room_1) :- !.
myRoom(djidjelly, room_2) :- !.

gotobed(Person) :- 
	family(Person, _, awake),
	myRoom(Person, Room),
	retractall(family(Person, _, _)),
	asserta(family(Person, Room, asleep)),
	turnLightOff(Room),
	turnRoomOff(Room).

%Seasons Tracker%
settime(Time,Hour, Day, Month) :-
	atomic(Day),
	atomic(Month),
	retractall(currenttime(_,_, _, _)),
	asserta(currenttime(Time,Hour, Day, Month)).

nextSeason(spring) :- currentseason(winter, _), !.
nextSeason(summer) :- currentseason(spring, _), !.
nextSeason(autumn) :- currentseason(summer,_), !.
nextSeason(winter).

changeseason(Temp) :-
	nextSeason(Next),
	season(Next),
	retractall(currentseason(_, _)),
	asserta(currentseason(Next, Temp)).

setseason(Temp) :-
	currenttime(_,_, _, Month),
	(
	    Month = 2;
	    Month = 3;
	    Month = 4
	),
	retractall(currentseason(_, _)),
	asserta(currentseason(spring, Temp)), !.
setseason(Temp) :-
	currenttime(_,_, _, Month),
	(
	    Month = 5;
	    Month = 6;
	    Month = 7
	),
	retractall(currentseason(_, _)),
	asserta(currentseason(summer, Temp)), !.
setseason(Temp) :-
	currenttime(_,_, _, Month),
	(
	    Month = 8;
	    Month = 9;
	    Month = 10
	),
	retractall(currentseason(_, _)),
	asserta(currentseason(autum, Temp)), !.
setseason(Temp) :-
	retractall(currentseason(_, _)),
	asserta(currentseason(winter, Temp)).
%Seasons Tracker%


%switchLFam%

switchFamL(Member,NewL):-
	family(Member,_,State),
	retractall(family(Member,_,_)),
	asserta(family(Member,NewL,State)).

%switchLFam%
%switch sensor%
switchSensor(Sens,State):-
	(state(Sens,motion);state(Sens,no_motion)),
	retractall(state(Sens,_)),
	asserta(state(Sens,State)).
	
salirLlegar(Pers):-
	family(Pers,out,awake),
	retractall(family(Pers,_,awake)),
	asserta(family(Pers,marquesina,awake)),!,fail.
salirLlegar(Pers):-
family(Pers,_,awake),
	retractall(family(Pers,_,awake)),
	asserta(family(Pers,out,awake)).	
%switch sensor%


everyone(Members) :- 
	findall(Member, family(Member, _, _), Members).

everyone(Members) :-
	findall(Member, family(Member, _, asleep), Members).

wakeEveryone :-
	everyone(Members),
	wakeFamily(Members).

shutAlarmD(_) :-
	retractall(house(_)),
	asserta(house(normal)).

warning(_):-
	wakeEveryone,
	allWindowsLock,
	allDoorsLock,
	allOn,
	retractall(house(_)),
	asserta(house(alarm)).
	