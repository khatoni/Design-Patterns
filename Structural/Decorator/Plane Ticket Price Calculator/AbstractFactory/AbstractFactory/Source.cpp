//#include <iostream>
//
//
//class IHttpService {
//
//};
//
//class HttpService : public IHttpService {
//
//};
//
//class MockHttpService : public IHttpService {
//
//};
//
//class IHttpFactory {
//public:
//	virtual IHttpService* create() = 0;
//};
//
//class HttpFactory : public IHttpFactory {
//public:
//	IHttpService* create() {
//		return new HttpService();
//	};
//};
//
//class MockHttpFactory : public IHttpFactory {
//public:
//	IHttpService* create() {
//		return new MockHttpService();
//	};
//};
//
//int main() {
//
//	IHttpFactory* factory;
//
//	if () {
//		factory = new HttpFactory();
//	}
//	else {
//		factory = new MockHttpFactory();
//	}
//
//	factory()->create();
//	return 0;
//}



//#include <iostream>
//
//int main() {
//
//	FileSystem* obj1 = new Directory();
//	FileSystem* obj2 = new File("aba");
//	FileSystem* obj3 = new File("d");
//	FileSystem* obj4 = new Directory();
//	obj1.add(obj2);
//	obj1.add(obj4);
//	obj4.add(obj3);
//
//
//}