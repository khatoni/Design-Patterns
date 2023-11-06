#pragma once
class WindowSeatDecorator : public Decorator {
public:
	WindowSeatDecorator(PlaneTicket* resource) : Decorator(resource) {
		this->hasPriority = true;
	}
	double getPrice() const override {
		return resource->getPrice() + 40;
	}
	std::string getDescription() const override {
		return resource->getDescription() + "Window Seat selected\n";
	}
	void ticketDetails() const override {
		std::cout << getDescription() + "\n the final price is \n" << getPrice();
	}
};

