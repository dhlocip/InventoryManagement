create database SIMS

use SIMS
use one

--===================================================================
-- 1. Users
--===================================================================
-- CREATE table Users

create table Users
(
    id int IDENTITY,
    [key] varchar(1) DEFAULT 'U',
    userId as ([key] + right('0000' + cast(id as varchar(4)), 4)) PERSISTED,
    fullName nvarchar(50),
    birthday date,
    hireDate date,
    [address] nvarchar(100),
    phone nvarchar(15),
    gender nvarchar(15),
    shiff nvarchar(20),
    userName nvarchar(20),
    [password] nvarchar(20),
    position nvarchar(20),
    email NVARCHAR(100),
    token VARCHAR(6),
    PRIMARY KEY (userId)
)

-- INSERT into Users
insert into Users
    (
    fullName, birthday, hireDate, [address], phone, gender, shiff, userName, [password], [position], email
    )
VALUES
    ('Nguyen Van A', '01/01/1990', '01/01/2021', 'Can Tho', '0948000948', 'Male', 'C12', 'admin', 'rootroot', 'Admin', 'dhlocc@gmail.com'),
    ('Nguyen Van B', '01/01/1992', '01/01/2021', 'Can Tho', '0948000959', 'Male', 'C12', 'inventory01', 'rootroot', 'Inventory Manager', 'dhlocc@gmail.com'),
    ('Nguyen Van C', '01/01/1993', '01/01/2021', 'Can Tho', '0948000919', 'Female', 'C12', 'salemM01', 'rootroot', 'Sale Manager', 'dhlocc@gmail.com'),
    ('Nguyen Van D', '01/01/1994', '01/05/2021', 'Can Tho', '0948000929', 'Male', 'C1', 'sale01', 'rootroot', 'Sale Person', 'dhlocc@gmail.com'),
    ('Nguyen Van E', '01/01/1995', '01/05/2021', 'Can Tho', '0948000939', 'Female', 'C2', 'sale02', 'rootroot', 'Sale Person', 'dhlocc@gmail.com'),
    ('Nguyen Van F', '01/01/1998', '01/05/2021', 'Can Tho', '0948000969', 'Male', 'C2', 'sale03', 'rootroot', 'Sale Person', 'dhlocc@gmail.com'),
    ('Nguyen Van G', '01/01/1996', '01/05/2021', 'Can Tho', '0948000949', 'Female', 'C1', 'sale04', 'rootroot', 'Sale Person', 'dhlocc@gmail.com')

select *
from Users
go

--===================================================================
-- 2. Categories
--===================================================================
-- CREATE table Categories
create table Categories
(
    id int IDENTITY,
    [key] varchar(1) DEFAULT 'C',
    categoryId as ([key] + right('0000' + cast(id as varchar(4)), 4)) PERSISTED,
    categoryName NVARCHAR(50),
    [description] text
    PRIMARY KEY (categoryId)
)
-- INSERT into Categories
insert into Categories 
    (categoryName)
values
    ('Type A'),
    ('Type B'),
    ('Type C')

select * from Categories
go

--===================================================================
-- 3. Products
--===================================================================
-- CREATE table Products
create table Products
(
    id int IDENTITY,
    [key] varchar(1) DEFAULT 'P',
    productId as ([key] + right('0000' + cast(id as varchar(4)), 4)) PERSISTED,
    productName nvarchar(100),
    categoryId varchar(5),
    price float,
    PRIMARY KEY (productId),
    FOREIGN KEY (categoryId) REFERENCES Categories(categoryId)
)
-- INSERT into Products 
insert into Products 
    (productName, categoryId, price)
VALUES
    ('Product 1', 'C0001', 50000),
    ('Product 2', 'C0001', 60000),
    ('Product 3', 'C0002', 70000),
    ('Product 4', 'C0002', 80000),
    ('Product 5', 'C0003', 90000),
    ('Product 6', 'C0003', 100000)

select * from Products
go


--===================================================================
-- 4. Suppliers
--===================================================================
-- CREATE table Suppliers
create table Suppliers
(
    id int IDENTITY,
    [key] varchar(1) DEFAULT 'S',
    supplierId as ([key] + right('0000' + cast(id as varchar(4)), 4)) PERSISTED,
    companyName nvarchar(100),
    [address] NVARCHAR(100),
    phone nvarchar(15),
    homePage nvarchar(100),
    personRepresentative nvarchar(100),
    PRIMARY KEY (supplierId)
)
-- INSERT into Suppliers
insert into Suppliers
    (companyName, [address], phone, homePage, personRepresentative)
VALUES
    ('Company A', 'Can Tho', '0948000123', '', 'Nguyen Thi A'),
    ('Company B', 'Can Tho', '0948000124', '', 'Nguyen Thi B'),
    ('Company C', 'Can Tho', '0948000125', '', 'Nguyen Thi C'),
    ('Company D', 'Can Tho', '0948000126', '', 'Nguyen Thi D')

select * from Suppliers
GO

--===================================================================
-- 5. ImportStocks
--===================================================================
-- CREATE table ImportStocks
create table ImportStocks
(
    id int IDENTITY,
    [key] varchar(1) DEFAULT 'I',
    importStockId as ([key] + right('0000' + cast(id as varchar(4)), 4)) PERSISTED,
    userId varchar(5),
    supplierId varchar(5),
    importDate date,
    PRIMARY KEY (importStockId),
    FOREIGN KEY (userId) REFERENCES Users(userId),
    FOREIGN KEY (supplierId) REFERENCES Suppliers(supplierId)
)
-- INSERT into ImportStocks
insert into ImportStocks
    (userId, supplierId, importDate)
values
    ('U0002', 'S0001', '05/01/2021'),
    ('U0002', 'S0002', '05/02/2021'),
    ('U0002', 'S0003', '05/03/2021'),
    ('U0002', 'S0004', '05/04/2021')

select * from ImportStocks
go

--===================================================================
-- 6. ImportStockDetail
--===================================================================
-- CREATE table ImportStockDetail
create table ImportStockDetail
(
    importStockId varchar(5),
    productId varchar(5),
    quantity int,
    price float,
    mfgDate date,
    expDate date,
    PRIMARY KEY (importStockId, productId),
    FOREIGN key (importStockId) REFERENCES ImportStocks(importStockId),
    FOREIGN key (productId) REFERENCES Products(productId)
)
-- INSERT into ImportStockDetail
insert into ImportStockDetail

VALUES
    ('I0001', 'P0001', 100, 60000, '01/01/2021', '01/01/2023'),
    ('I0001', 'P0002', 100, 70000, '01/01/2021', '01/01/2023'),
    ('I0002', 'P0003', 100, 80000, '01/01/2021', '01/01/2023'),
    ('I0002', 'P0004', 100, 90000, '01/01/2021', '01/01/2023'),
    ('I0003', 'P0005', 100, 160000, '01/01/2021', '01/01/2023'),
    ('I0003', 'P0006', 100, 260000, '01/01/2021', '01/01/2023'),
    ('I0004', 'P0001', 100, 360000, '01/01/2021', '01/01/2024'),
    ('I0004', 'P0002', 100, 460000, '01/01/2021', '01/01/2023')

select * from ImportStockDetail
GO

--===================================================================
-- 7. Requests
--===================================================================
-- CREATE table Requests
create table Requests
(
    id int IDENTITY,
    [key] varchar(1) DEFAULT 'R',
    requestId as ([key] + right('0000' + cast(id as varchar(4)), 4)) PERSISTED,
    userId varchar(5),
    startDate date,
    -- sua datetime > date tren ER sau
    statusVerify NVARCHAR(10),
    PRIMARY KEY (requestId),
    FOREIGN key (userId) REFERENCES Users(userId)
)

-- INSERT into Requests
insert into Requests
    (
    userId, startDate, statusVerify
    )
VALUES
    ('U0004', '06/15/2021', ''),
    ('U0005', '06/16/2021', 'NO'),
    ('U0006', '06/17/2021', 'YES'),
    ('U0007', '06/18/2021', '')

select * from Requests
go

--===================================================================
-- 8. RequestDetail
--===================================================================
-- CREATE table RequestDetail
create table RequestDetail
(
    requestId varchar(5),
    productId varchar(5),
    quantity int,
    PRIMARY KEY (requestId, productId),
    FOREIGN KEY (requestId) REFERENCES Requests(requestId),
    FOREIGN KEY (productId) REFERENCES Products(productId)
    --chua create products
)

-- INSERT into RequestDetail
insert into RequestDetail
values
    ('R0001', 'P0001', 100),
    ('R0001', 'P0002', 90),
    ('R0001', 'P0003', 80),
    ('R0002', 'P0004', 70),
    ('R0002', 'P0005', 60),
    ('R0003', 'P0006', 70),
    ('R0003', 'P0001', 80),
    ('R0004', 'P0002', 90),
    ('R0004', 'P0003', 100),
    ('R0004', 'P0004', 90)

select * from RequestDetail
GO

--===================================================================
-- 9. NewRequests
--===================================================================
-- CREATE table NewRequests
create table NewRequests
(
    id int IDENTITY,
    [key] varchar(1) DEFAULT 'N',
    newRequestId as ([key] + right('0000' + cast(id as varchar(4)), 4)) PERSISTED,
    userId varchar(5),
    startDate date,
    -- sua datetime > date tren ER sau
    statusVerify NVARCHAR(10),
    PRIMARY KEY (newRequestId),
    FOREIGN key (userId) REFERENCES Users(userId)
)

-- INSERT into NewRequests
insert into NewRequests
    (
    userId, startDate, statusVerify
    )
VALUES
    ('U0004', '06/24/2020', ''),
    ('U0005', '06/25/2020', 'NO'),
    ('U0006', '06/26/2020', 'NO'),
    ('U0007', '06/27/2020', 'YES')

select * from NewRequests
go

--===================================================================
-- 10. NewRequestDetail
--===================================================================
-- CREATE table NewRequestDetail
create table NewRequestDetail
(
    newRequestId varchar(5),
    newProductName nvarchar(50),
    quantity int,
    PRIMARY KEY (newRequestId),
    FOREIGN KEY (newRequestId) REFERENCES NewRequests(newRequestId)
)

-- INSERT into NewRequestDetail
insert into NewRequestDetail
VALUES
    ('N0001', 'Product n1', 20),
    ('N0002', 'Product n3', 20),
    ('N0003', 'Product n5', 20),
    ('N0004', 'Product n8', 20)

select * from NewRequestDetail
go

--===================================================================
-- 11. Bills
--===================================================================
-- CREATE table Bills 
create table Bills
(
    id int IDENTITY,
    [key] varchar(1) DEFAULT 'B',
    billId as ([key] + right('0000' + cast(id as varchar(4)), 4)) PERSISTED,
    userId varchar(5),
    transactionDate datetime,
    statusCancel nvarchar(10),
    paymentName nvarchar(20), 
    PRIMARY KEY (billId),
    FOREIGN KEY (userId) REFERENCES Users(userId)
)
-- INSERT into Bills
insert into Bills
    (userId, transactionDate, statusCancel, paymentName)
VALUES
    ('U0004', '06/01/2021', '', 'cash'),
    ('U0004', '06/02/2021', 'YES', 'cash'),
    ('U0005', '06/03/2021', '', 'card'),
    ('U0006', '06/04/2021', '', 'card'),
    ('U0007', '06/05/2021', 'YES', 'cash')

select * from Bills
go
--===================================================================
-- 12. BillDetail
--===================================================================
-- CREATE table BillDetail
create table BillDetail
(
    productId VARCHAR(5),
    billId VARCHAR(5),
    quantity int,
    mfgDate date,
    expDate date,
    PRIMARY KEY (productId, billId),
    FOREIGN KEY (productId) REFERENCES Products(productId),
    FOREIGN KEY (billId) REFERENCES Bills(billId)
)
-- INSERT into BillDetail
insert into BillDetail
    (productId, billId, quantity, mfgDate, expDate)
VALUES
    ('P0001', 'B0001', 10, '01/01/2021', '01/01/2023'),
    ('P0002', 'B0001', 10, '01/01/2021', '01/01/2023'),
    ('P0003', 'B0001', 10, '01/01/2021', '01/01/2023'),

    ('P0004', 'B0002', 10, '01/01/2021', '01/01/2023'),
    ('P0005', 'B0002', 10, '01/01/2021', '01/01/2023'),

    ('P0006', 'B0003', 10, '01/01/2021', '01/01/2023'),
    ('P0001', 'B0003', 10, '01/01/2021', '01/01/2024'),

    ('P0002', 'B0004', 10, '01/01/2021', '01/01/2023'),
    ('P0003', 'B0004', 10, '01/01/2021', '01/01/2023'),

    ('P0004', 'B0005', 10, '01/01/2021', '01/01/2023'),
    ('P0005', 'B0005', 10, '01/01/2021', '01/01/2023'),
    ('P0006', 'B0005', 10, '01/01/2021', '01/01/2023')

select * from BillDetail
go

--===================================================================
-- 13. Events
--===================================================================
-- CREATE table Events
create table Events
(
    id int IDENTITY,
    [key] varchar(1) DEFAULT 'E',
    eventId as ([key] + right('0000' + cast(id as varchar(4)), 4)) PERSISTED,
    userId varchar(5),
    eventName nvarchar(50),
    startDate date,
    endDate date,
    PRIMARY KEY (eventId),
    FOREIGN KEY (userId) REFERENCES Users(userId)
)
-- INSERT into Events
insert into Events 
    (userId, eventName, startDate, endDate)
VALUES
    ('U0004', 'Event 1', '02/02/2020', '02/05/2020'),
    ('U0005', 'Event 2', '02/10/2020', '02/15/2020'),
    ('U0006', 'Event 3', '02/20/2020', '02/25/2020')

select * from Events
go
--===================================================================
-- 14. EventDetail
--===================================================================
-- CREATE table EventDetail
create table EventDetail
(
    eventId varchar(5),
    productId varchar(5),
    discount float,
    mfgDate date,
    expDate date,
    PRIMARY KEY (eventId, productId),
    FOREIGN KEY (eventId) REFERENCES Events(eventId),
    FOREIGN KEY (productId) REFERENCES Products(productId)
)
-- INSERT into EventDetail
insert into EventDetail
    (eventId, productId, discount, mfgDate, expDate)
values 
    ('E0001', 'P0001', 0.1, '01/01/2021', '01/01/2023'),
    ('E0001', 'P0002', 0.1, '01/01/2021', '01/01/2023'),
    ('E0002', 'P0003', 0.15, '01/01/2021', '01/01/2023'),
    ('E0002', 'P0004', 0.2, '01/01/2021', '01/01/2023'),
    ('E0003', 'P0005', 0.15, '01/01/2021', '01/01/2023'),
    ('E0003', 'P0006', 0.1, '01/01/2021', '01/01/2023')

select * from EventDetail
GO

--Tao view Event
create view VEvent AS
select Events.eventId as eventId, Events.userId as userId, Events.eventName as eventName, Events.startDate as startDate, 
Events.endDate as endDate, EventDetail.productId as productId,
EventDetail.discount as discount, EventDetail.mfgDate as mfgDate, EventDetail.expDate as expDate
from Events inner JOIN EventDetail
on Events.eventId = EventDetail.eventId

select * from VEvent
go


--tao view New Reuquest

create view VNewRequest AS
select NewRequests.newRequestId, NewRequests.userId, NewRequests.startDate,
NewRequests.statusVerify, NewRequestDetail.newProductName, NewRequestDetail.quantity
from NewRequests inner JOIN NewRequestDetail
on NewRequests.newRequestId = NewRequestDetail.newRequestId

select * from VNewRequest
go


--tao view request

create view VRequest AS
select Requests.requestId, Requests.userId, Requests.startDate,Requests.statusVerify,
RequestDetail.productId, RequestDetail.quantity
from Requests inner JOIN RequestDetail
on Requests.requestId = RequestDetail.requestId

select * from VRequest
go


-- tạo view Bill cho hai bảng thống kê đơn hàng (Statistics by Date và Statistics by Cancel)
create view VBills AS
select Bills.billId, Bills.userId, Bills.transactionDate, Bills.paymentName,Bills.statusCancel,
BillDetail.productId, BillDetail.quantity, BillDetail.mfgDate, BillDetail.expDate
from Bills inner JOIN BillDetail
on Bills.billId = BillDetail.billId

select * from VBills
go