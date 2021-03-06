package com.metro.presentation;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;

import com.metro.bean.MetroCard;
import com.metro.bean.Station;
import com.metro.service.MetroServiceImpl;
import com.metro.service.NotEnoughBalance;
import com.station.service.StationServiceImpl;

public class MetroPresentationImpl implements MetroPresentation {
	
//	private static int cardId=0, stationId=0;
	
	private MetroServiceImpl metroService=new MetroServiceImpl();
	private StationServiceImpl stationService = new StationServiceImpl();

	@Override
	public void showMenu() {
		System.out.println("WELCOME TO METRO SYSTEM");
		System.out.println("1. Create a new Metro Card");
		System.out.println("2. Remove Existing Card");
		System.out.println("3. Check Balance");
		System.out.println("4. Add Balance");
		System.out.println("5. Swipe In");
		System.out.println("6. Swipe Out");
		System.out.println("7. Exit");
		System.out.println("8. Display");
		System.out.println("9. Add More Stations:");
	}

	@Override
	public void performMenu(int choice) throws ClassNotFoundException, SQLException {
		Scanner scanner = new Scanner(System.in);
		switch(choice){
		case 1://create metrocard
			int id;
			System.out.println("Enter the balance to be added");
			int balance = scanner.nextInt();
			if(balance>100){
				id = metroService.addCard(balance);
				System.out.println("Your new card is generated with ID: "+id);
			}
			else
				System.out.println("Balance should atleast be Rs.100s");
			
			break;
		case 2://remove card
			System.out.println("Enter the card ID to remove:");
			if(metroService.removeCard(scanner.nextInt()))
				System.out.println("Card Removed Successfully");
			else
				System.out.println("Card Removal Failed!");
			break;
		case 3://check balance
			System.out.println("Enter the card ID");
			System.out.println("Balance: "+metroService.balance(scanner.nextInt()));
			break;
		case 4://add balance
			System.out.println("Enter the card ID and Amount");
			if(metroService.addBalance(scanner.nextInt(), scanner.nextInt()))
				System.out.println("Balance Added Succesfully");
			else
				System.out.println("Balance Addition Failed!");
			break;
		case 5://swipein
			try {
			System.out.println("Enter Card ID:");
			int idIn = scanner.nextInt();
			metroService.hasBalance(idIn);
//			System.out.println(metroService.swipedIn(idIn));
			if((metroService.swipedIn(idIn))==false){
				System.out.println("Enter Source Station:");
				int stationId = scanner.nextInt();
//				metroService.isValidStation(stationId);
				metroService.swipeIn(idIn,stationId);
			}
			else
				System.out.println("Card is already Swiped in! Kindly Swipe out to continue");
			}catch(NotEnoughBalance e) {
				System.out.println("Not Enough Balance");
			}
			catch(ClassNotFoundException e) {
				System.out.println("We have reached a Class Not Found Exception!");
			}
			catch(SQLException e) {
				System.out.println("Oh hoo! There happens to be a SQL Exception!");
			}
			

			break;
		case 6://swipeout
			System.out.println("Enter Card ID:");
			int idOut = scanner.nextInt();
//			System.out.println(metroService.swipedIn(idOut));
			if(((metroService.swipedOut(idOut)))==false){
				System.out.println("Enter Destination Station:");
				int stationId = scanner.nextInt();
				metroService.swipeOut(idOut,stationId);
			}
			else
				System.out.println("Card is already Swiped out! Kindly Swipe in to continue");
			break;
		case 7:
			System.out.println("Thank You For Using Metro Service! Have a nice day!");
			System.exit(0);
		case 8:
			System.out.println("========All cards=======");
			Collection<Station> stations  =  stationService.getAllstations();
			for(Station station:stations) {
				System.out.println(station);
			}
//			Collection<MetroCard> cards=metroService.getAllcards();
//			for(MetroCard card:cards) {
//				System.out.println(card);
//			}
			break;
		case 9:
			System.out.println("Enter the station name");
			String stationName = scanner.nextLine();
			if(stationService.addStation(stationName)) {
				System.out.println("Congratulations Station: "+stationName+" has been added!");
			}
			break;
		}

	}
}