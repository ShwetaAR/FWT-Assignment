package com.yash.poc;

import java.util.Arrays;
import java.util.Scanner;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class SeatingArrangment {

	private static Scanner scanner;
	private static int numberOfRows;
	private static int numberOfseats;
	static Seat seat;
	 public SeatingArrangment() {
		  seat = new Seat();
	}

	public static void main(String[] args) {
		SeatCategory seatCategory = new SeatCategory();
		Seat seat = new Seat();
		System.out.println("Select Seat catergory:\n ");
		System.out.println("1: Premium \n2: Silver \n3:Gold \n ");
		scanner = new Scanner(System.in);
		int category = scanner.nextInt();
		switch (category) {
		case 1:
			selectionForPremimumCategory();
			break;
		case 2:

			break;
		case 3:

			break;

		default:
			break;
		}

	}

	private static void selectionForPremimumCategory() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of rows");
		 numberOfRows = scanner.nextInt();

		System.out.println("Enter number of seats for row 1");
		 numberOfseats = scanner.nextInt();
		int seatNUmbers = numberOfseats;
		String s = "";
		char charvalue ='A';
		int[][] seats = new int[numberOfRows][numberOfseats];
		for (int i = 0; i < numberOfRows; i++) {

			s = "P" + (i + 1);
			int count = 1;
			System.out.print(s);
			for (int k = 0; k < i + 1; k++) {
				System.out.print("\t");
			}
			for (int l = 0; l < numberOfseats; l++) {
				int value = ((i+1)*10)+count;
				System.out.print(((i+1)*10)+count + "\t");
				seats[i][l]=value;
			
				
				//System.err.print(seats[i][l]);
				count++;
			}
			System.out.println("\n");
			numberOfseats = numberOfseats - 2;

		}
		for (int i = 0; i < numberOfRows; i++) {
			s = "P" + (i + 1);
			System.out.print(s);
			for (int k = 0; k < (i + 1); k++) {
				System.out.print("\t");
			}
			for (int l = 0; l < seatNUmbers; l++) {
				System.out.print(seats[i][l]+"\t");
			}
			System.out.println("\n");
			seatNUmbers = seatNUmbers - 2;
			}
		
		}


	}


