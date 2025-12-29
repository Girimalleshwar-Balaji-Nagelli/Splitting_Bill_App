In a restaurant billing system, each order is associated with a table ID. Each table consists of seats, representing the number of people sharing the order. The bill amount can be split among the individuals using different methods based on the user’s preference.
1. Split Evenly
The total bill amount is divided equally among all individuals seated at the table.


Example: If the total bill is $100 and there are 4 persons, each person pays $25.


2. Split by Item
The bill is split based on the specific items selected for each person.


Each person is assigned items they consumed, and the respective item costs are summed to determine their individual payment.


Example:


Person A orders Burger ($10) and Drink ($5) → Pays $15


Person B orders Pizza ($12) → Pays $12


3. Split by Amount
The user manually assigns an amount to each person.


The total assigned amount should not exceed the total bill.


Example:


Person A pays $40


Person B pays $30


Person C pays $30


Total bill = $100


4. Split by Shares
The bill is divided based on the number of shares assigned to each person.


Example:


Person A has 2 shares


Person B has 1 share


Person C has 1 share


If the total bill is $100, then:


Person A pays $50 (2/4 shares)


Person B pays $25 (1/4 shares)


Person C pays $25 (1/4 shares)


5. Split by Percentage
Each person is assigned a percentage of the total bill.


Example:


Person A pays 50% of the bill


Person B pays 30% of the bill


Person C pays 20% of the bill


If the total bill is $100, the payments would be:


Person A → $50


Person B → $30


Person C → $20

Splitting Bill App video :-

https://github.com/user-attachments/assets/e1951482-a28d-4b0c-a8fc-f2d0f294b67c

