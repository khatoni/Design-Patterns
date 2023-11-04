#pragma once
#include "PlaneTicket.h"
class DomesticFlight : public  PlaneTicket {
public:
	DomesticFlight(double price, std::string from, std::string to) :PlaneTicket(price, from, to) {
		description = description + " type of flight : Domestic flight\n";
	};
	double getPrice() const override {
		return price;
	}
	std::string getDescription()const {
		return description;
	}
};

