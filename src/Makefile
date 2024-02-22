deafult: run

run: FrontEnd.class
	java -cp .:gson-2.9.0.jar FrontEnd

FrontEnd.class: BackEnd.class FrontEnd.java 
	javac -cp .:gson-2.9.0.jar FrontEnd.java

BackEnd.class: HashTableMap.class Park.class Text.class TravelPlan.class BackEnd.java gson-2.9.0.jar
	javac -cp .:gson-2.9.0.jar BackEnd.java

Activity.class: Activity.java
	javac Activity.java

Text.class: Text.java
	javac Text.java

Ticket.class: Ticket.java
	javac Ticket.java

OperatingHour.class: OperatingHour.java
	javac OperatingHour.java

Park.class: Ticket.class Activity.class OperatingHour.class Park.java
	javac Park.java

HashTableMap.class: MapADT.class HashTableMap.java
	javac HashTableMap.java

MapADT.class: MapADT.java
	javac MapADT.java

TravelPlan.class: TravelPlan.java HashTableMap.class
	javac TravelPlan.java

clean:
	rm *.class

TestNationalPark.class: TestNationalPark.java FrontEnd.class
	javac -cp .:gson-2.9.0.jar:junit5.jar TestNationalPark.java -Xlint

test: TestNationalPark.class
	java -jar junit5.jar --class-path . --scan-classpath
