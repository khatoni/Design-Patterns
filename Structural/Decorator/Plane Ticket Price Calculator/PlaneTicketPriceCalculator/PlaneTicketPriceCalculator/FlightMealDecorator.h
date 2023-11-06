#pragma once
#include "Decorator.h"
class FlightMealDecorator : public Decorator {

public:
	FlightMealDecorator(PlaneTicket* resource) : Decorator(resource) {
		this->hasMeal = true;
	};
	double getPrice() const override {
		return resource->getPrice() + 20;
	}
	std::string getDescription() const override {
		return resource->getDescription() + "Included flight meal\n";
	}
	void ticketDetails() const override {
		std::cout << getDescription() + "\n the final price is \n" << getPrice();
	}
};

