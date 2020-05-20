Mailroom Delivery Service Hashmaps and Queues

Description of Program: I am creating a Mailroom Delivery Service
that handles two types of items, letter mails and packages, using using 
Hashmaps and queues. Hashmaps organizes all the packages/mail to the same 
zipcode so it can be delivered. Queues are organize the individual packages
and mail that arerelated to the zipcode and place dependencies on each of them.
The order of the packages and mails are organized by its registered time.

Features: To organize all mails and packages into same queue, there are
add and remove methods that put them together. You can look at the first
package/mail with peek and check the states of the queue such as the size and
number of elements. In Hashmaps, which is the mailroom, it can give each item
a registered time in order to do to decide which items should be delivered
first. Delivery can also depend on weight. Similar zipcodes can be grouped
and delivered together as well.
