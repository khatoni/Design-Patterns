#include "PlaneTicket.h"
#include "DomesticFlight.h"
#include "InternationalFlight.h"
#include "Decorator.h"
#include "DiscountDecorator.h"
#include "FlightMealDecorator.h"
#include "LuggageDecorator.h"
#include "PriorityBoardingDecorator.h";
#include "WindowSeatDecorator.h";
#include <iostream>

int main() {

	PlaneTicket* first = new DomesticFlight(80, "Sofia", "Varna");
	first = new DiscountDecorator(first, 20);
	first = new FlightMealDecorator(first);
	first = new LuggageDecorator(first);
	first = new PriorityBoardingDecorator(first);
	first = new WindowSeatDecorator(first);
	std::cout << first->getDescription() << " \n " << first->getPrice();
	return 0;
}