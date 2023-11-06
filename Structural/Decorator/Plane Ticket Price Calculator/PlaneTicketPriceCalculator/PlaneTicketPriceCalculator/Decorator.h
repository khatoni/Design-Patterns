#pragma once
#include "PlaneTicket.h"
class Decorator : public PlaneTicket {

protected:
	PlaneTicket* resource;

public:
	Decorator(PlaneTicket* resource) : resource(resource) {};
	virtual void ticketDetails() const = 0;

};

