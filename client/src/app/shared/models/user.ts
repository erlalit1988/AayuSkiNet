export type User = {
    firstName: string;
    lastName: string;
    email: string;
    address: Address;
}

export type Address = {
    line1: String;
    line2?: string;
    city: string;
    state:string;
    country:string;
    postalCode: string;
}