import { salesperson } from "../models/salesperson";


export class sales  {
    sid: number;
   sname: String;
    department: String;
    joiningDate: Date;
    status: String;
    iscontractbase:string;
    contact_no:number;
    about:string;
    userUrl:string;
    country:string;
    // states=[];
    currency:string;
    password:string;
    vehicles:string;
    email:string;
    // uploadprofile:string;
    salesperson: salesperson[];
}