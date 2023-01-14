const rootUser = process.env["MONGO_INITDB_ROOT_USERNAME"];
const rootPassword = process.env["MONGO_INITDB_ROOT_PASSWORD"];
const user = process.env["MONGO_INITDB_USERNAME"];
const pwd = process.env["MONGO_INITDB_PASSWORD"];
const database = process.env["MONGO_INITDB_DATABASE"];

const admin = db.getSiblingDB('admin');
admin.auth(rootUser, rootPassword);

const shopping = db.getSiblingDB(database)
shopping.createUser(
    {
        user,
        pwd,
        roles: [
            "readWrite", "dbAdmin"
        ]
    }
);

shopping.products.insertMany([
    {_id: UUID("e737b48b-1fe2-476a-ba2e-85c44f036e86"), name: "Apple iPhone 14 Pro Max", price: "6499.00"},
    {_id: UUID("a8ef1604-79a0-4b0a-88af-3a131934398f"), name: "Samsung Galaxy S22 Ultra", price: "5199.00"},
    {_id: UUID("09716a47-ab35-4753-8f12-b52e0f9ceca2"), name: "ASUS Zenfone 9", price: "3799.00"},
    {_id: UUID("3c0f02b9-ac9f-4d1e-9039-17007f2aa606"), name: "HUAWEI P50 Pro", price: "4999.00"},
    {_id: UUID("5894f902-3f63-4aac-9ef2-40c8551e2ac3"), name: "Xiaomi 12T Pro", price: "3099.00"},
]);
