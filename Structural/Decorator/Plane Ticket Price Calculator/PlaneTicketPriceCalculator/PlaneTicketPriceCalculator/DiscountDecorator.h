#pragma once
#include "Decorator.h"
class DiscountDecorator : public Decorator {
private:
	int discount;

public:
	DiscountDecorator(PlaneTicket* resource, int discount) : Decorator(resource) {
		this->discount = discount;
	}
	double getPrice()const override {
		return resource->getPrice() * (100 - discount) / 100;
	}
	std::string getDescription()const override {
		return resource->getDescription() + "Discount is included\n";
	}
	void ticketDetails()const override {
		std::cout << getDescription() + "\n the final price is \n" << getPrice();
	}


};

