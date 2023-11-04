#pragma once
#include <iostream>
class PlaneTicket {
protected:
	double price;
	std::pair< int, char> seat;
	bool hasLuggage;
	bool hasPriority;
	bool hasMeal;
	std::string from;
	std::string to;
	std::string description;
public:
	PlaneTicket() = default;
	PlaneTicket(double price, std::string from, std::string to) {
		this->price = price;
		this->from = from;
		this->to = to;
		description = "Flight from " + from + " to " + to + '\n';

	}
	virtual double getPrice() const = 0;
	//virtual std::pair<int, char> getSeat()const = 0;
	virtual ~PlaneTicket() = default;
	virtual std::string getDescription()const = 0;
};

