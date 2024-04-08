#include <iostream>

class Singleton {
	
	Singleton() = default;

public:

	Singleton(const Singleton& other) = delete;
	Singleton& operator=(const Singleton& other) = delete;

	static Singleton* getInstance() {
		static Singleton instance;
		return &instance;
	}
};

int main() {

	Singleton* s1 = Singleton::getInstance();
	Singleton* s2 = Singleton::getInstance();
	Singleton* s3 = Singleton::getInstance();

	if (s1 == s2 && s2 == s3) {
		std::cout << "It is the same instance\n";
	}
	return 0;
}