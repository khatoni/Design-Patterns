#pragma once
#include "Decorator.h"
class PriorityBoardingDecorator : public Decorator {
public:

	PriorityBoardingDecorator(PlaneTicket* resource) : Decorator(resource) {
		this->hasPriority = true;
	}
	double getPrice()const override {
		return resource->getPrice() + 20;
	}
	std::string getDescription()const override {
		return resource->getDescription() + "PriorityBoarding included\n";
	}
	void ticketDetails()const override {
		std::cout << getDescription() + "\n the final price is \n" << getPrice();
	}
};

