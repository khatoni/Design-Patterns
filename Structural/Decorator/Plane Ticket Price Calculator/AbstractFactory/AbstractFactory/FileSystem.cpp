#include <iostream>
#include <vector>
#include <Iterator>
using namespace std;
class Filesystem {
protected:
	int size;
	std::string data;
public:
	Filesystem() {
		this->size = 1;
		data = "";
	}
	Filesystem(std::string& data) {
		size = data.length();
		this->data = data;

	}

	virtual void add(Filesystem* object) = 0;
	virtual void remove(Filesystem* object) = 0;
	virtual void getContent() const = 0;
	virtual bool isDirectory() const = 0;
	virtual int getSize() const = 0;
	virtual ~Filesystem() = default;
};
class MyFile : public Filesystem {
public:

	MyFile(std::string& data) : Filesystem(data) {};

	void add(Filesystem* object) {
		throw std::logic_error("cannot add to the subtree of a file");
	}

	void remove(Filesystem* object) {
		throw std::logic_error("cannot remove from a file subtree");
	}

	int getSize() const override {
		return size;
	}

	bool isDirectory() const override {
		return 0;
	}
	void getContent() const override {
		std::cout << this->data << " ";
	}
};
class Directory : public Filesystem {
private:
	std::vector<Filesystem*> elements;
public:

	Directory() : Filesystem() {};

	void add(Filesystem* object) {
		elements.push_back(object);
	}

	void remove(Filesystem* object) {
		for (auto i = elements.begin(); i != elements.end(); i++) {
			if (*i == object) {
				elements.erase(i);
			}
		}
	}

	bool isDirectory() const override {
		return 1;
	}

	int getSize() const override {

		int total = 1;
		for (auto i = 0; i < elements.size(); i++) {

			total += elements[i]->getSize();
		}
		return total;
	}

	void getContent() const override {
		std::cout << "Directory " << this->data <<std::endl;
		for (int i = 0; i < elements.size(); i++) {
			elements[i]->getContent();
		}
	}
};

#include <iostream>

int main() {

	std::string name1 = "wefwe";

	std::string name2 = "afa";
	Filesystem* obj1 = new Directory();
	Filesystem* obj2 = new MyFile(name1);
	Filesystem* obj3 = new MyFile(name2);
	Filesystem* obj4 = new Directory();

	obj1->add(obj2);
	obj1->add(obj4);
	obj4->add(obj3);

	std::cout << obj1->getSize() << std :: endl;

	obj1->getContent();

}