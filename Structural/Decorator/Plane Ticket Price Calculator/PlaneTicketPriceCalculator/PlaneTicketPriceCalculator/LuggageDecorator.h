#pragma once
#include "Decorator.h"
class LuggageDecorator : public Decorator {

public:
	LuggageDecorator(PlaneTicket* resource) : Decorator(resource) {
		this->hasLuggage = true;
	}
	double getPrice() const override {
		return resource->getPrice() + 50;
	}
	std::string getDescription() const override {
		return resource->getDescription() + "Luggage is included\n";
	}
	void ticketDetails() const override {
		std::cout << getDescription() + "\n the final price is \n" << getPrice();
	}

};

