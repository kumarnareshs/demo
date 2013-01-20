package my.app;


public class Connection {


	String name,address,doorno,location,regno;
	int phone,balance,stdamount,noofconnection,offeramount;
	String disconnect,freeconnection,stdsign;
	String date;
	
	//Delete  constructor this latter
	Connection(String name,String address,String doorno,String location,
			int phone,int balance,int stdamount,int noofconnection,int offeramount,
			String disconnect,String freeconnection,String stdsign,String date){
		
		 this.name=name;
		 this.address=address;
		 this.doorno=doorno;
		 this.location=location;
		 this.phone=phone;
		 this.balance=balance;
		 this.stdamount=stdamount;
		 this.noofconnection=noofconnection;
		 this.offeramount=offeramount;
		 this.disconnect=disconnect;
		 this.freeconnection=freeconnection;
		 this.stdsign=stdsign;
		 this.date=date;		 
	}
	
	Connection(String regno,String name,String address,String doorno,String location,
			int phone,int balance,int stdamount,int noofconnection,int offeramount,
			String disconnect,String freeconnection,String stdsign,String date){
		 this.regno=regno;
		 this.name=name;
		 this.address=address;
		 this.doorno=doorno;
		 this.location=location;
		 this.phone=phone;
		 this.balance=balance;
		 this.stdamount=stdamount;
		 this.noofconnection=noofconnection;
		 this.offeramount=offeramount;
		 this.disconnect=disconnect;
		 this.freeconnection=freeconnection;
		 this.stdsign=stdsign;
		 this.date=date;		 
	}
	
	
	String getregno() {return regno;}
    void setregno(String regno){ this.regno=regno;}
    
	String getname() {return name;}
    void setname(String name){ this.name=name;}

    String getaddress() {return address;}
    void setaddress(String address){ this.address=address;}
    
    String getdoorno() {return doorno;}
    void setdoorno(String doorno){ this.doorno=doorno;}
    
    String getlocation() {return location;}
    void setlocation(String location){ this.location=location;}
    
    int getphone() {return phone;}
    void setphone(int phone){ this.phone=phone;}
    
    int getbalance() {return balance;}
    void setbalance(int balance){ this.balance=balance;}
    
    int getstdamount() {return stdamount;}
    void setstdamount(int stdamount){ this.stdamount=stdamount;}
    
    int getnoofconnection() {return noofconnection;}
    void setnoofconnection(int noofconnection){ this.noofconnection=noofconnection;}

    int getofferamount() {return offeramount;}
    void setofferamount(int offeramount){ this.offeramount=offeramount;}
    
    String getdisconnect() {return disconnect;}
    void setdisconnect(String disconnect){ this.disconnect=disconnect;}

    String getfreeconnection() {return freeconnection;}
    void setfreeconnection(String freeconnection){ this.freeconnection=freeconnection;}

    String getstdsign() {return stdsign;}
    void setstdsign(String stdsign){ this.stdsign=stdsign;}

    String getdate() {return date;}
    void setdate(String date){ this.date=date;}

}   
