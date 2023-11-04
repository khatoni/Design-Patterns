#pragma once
#include "PlaneTicket.h"
class InternationalFlight : public PlaneTicket {
public:
	InternationalFlight(double price, std::string from, std::string to) : PlaneTicket(price, from, to) {
		description = description + " type of flight : International flight\n";
	}
	double getPrice() const override {
		return price;
	}
	std::string getDescription()const override {
		return description;
	}
};

