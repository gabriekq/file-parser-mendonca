
I create this program to exemplify one-parse file operations,
which include an end-point that read json files and record both file and the file´s information. 

The application also display the files saved in the database through and allowed it to download. 

I implement at this version the pagination that limits the rows per page. 

-the HTML without any data is load first, then the java script code in the page loads the data by call another end-point responsible to give the json data to finally fetch the table.

- the 7th version includes the search approach, that works by typing the id in this format 4-10 or just the id number like 4, the opposite also works 10-4.- 

- When the search field goes back to the original state "", the first page is displaying for the user.

- The end-point to accesses the main Paige is http://localhost:8081/home 

- before you start the application make sure the file called “application.properties” has the information about the E-mail server and also data base information.

- The file format that you have to send to save in the database is this one, if you type some email address and upload the file one message is going to be send to that address, if you type null no email is going to be send.

{
  "firstName": "NewGya",
  "lastName": "aaaa",
  "age": 50,
  "street": "abc avenue ",
  "emaill": "yourMail@abc.com"
}  

- At this version I also implement multithread FileServiceInpl class into the method called parseUploadedFile where there are some operations in the database and email that it’s not relate to each order.
