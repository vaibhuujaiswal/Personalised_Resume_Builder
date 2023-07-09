
# Personalised Resume Builder (UI and API)

The repository contains a personalised resume builder (API and User Interface) which takes user input either through the user interace (web interface) or through a curl request via the API in the form of json and merges with preexisting choosen template. The API returns your personalised resume in form of pdf. This project is tailored made as a solution to Adobe Document Cloud Hackathon - Round 2 submission

# Documentation

Clone the repository to your local machine by running the following command in terminal.

```shell
git clone "https://github.com/vaibhuujaiswal/Personalised_Resume_Builder.git"

```

## Run Locally

Clone the project

```bash
  git clone "https://github.com/vaibhuujaiswal/Personalised_Resume_Builder.git"
```

Go to the project directory

```bash
  cd Personalised_Resume_Builder
```

Install dependencies

```bash
  npm install
```

Start the React Project

```bash
  npm run start
```

(Make sure API server is running (demo)
## Repository content

1) *src* : Contains the Main React files including App.js, App.css, Index.js and Index.css
2) *public* : Contains React App Resources.
3) *demo* : Contains the Resume Builder API. (Made based on the API Specification)

## 1) Resume Builder API (demo)

Resume builder API has end point POST /resume. The request header is content type : application/json and accept application/pdf. The request object was created based on specification (list of personal, academic and professional experience) sent in the form of json. The response headers content-type is application/pdf and the response object is returned in PDF format.

Spring boot creates a POST Mapping which accepts the json from client into class ResumeRequest using RequestBody annotation. 

The ResumeRequest further has classes of PersonalInformation, Achievement, Education and Experience to structure the classes properly. We then use resumeRequestSerializer which has extended jackson JsonSerializer. 

It helps us use the information stored in the instance of ResumeRequest. It uses the jsonGenerator to write the data stored in the instance with the json format accepted by the Document Generation API. The template ID has been mapped with the name of the docx present in the resources file. 

We then use ObjectMapper and simpleModule to register add our serializer to our module with resumeRequestSerializer function and registers the module in the objectMapper. The objectMapper finally writes the value as string to JsonObject. 

The credentials are verified with the Adobe API and the pdf file is saved in FileRef. Now one can either directly save the file in local Repository. 

Now, since we had to respond with content type as PDF. We would create a ByteArrayOutputStream and save the output in the same. The converted ByteArray is the added to response entity with http headers indicating the media type as pdf and file name.

The API handles errors by using try catch statements and sending responseEntity with httpStatus as well as logging the error using Logger.

Error Handling -> 
400 : Bad Request 
401 : Unauthorised 
404 :Template not found 
500 : Internal Server Error

### Access and Running API
To allow a local host to access the server (CORS), @CrossOrigin was used to add origin as localhost : 3000. 

The API is generally accesible through http://localhost:8080/resume.

To run the server : Go to the demo file and navigate MergeDocumentToDocxApplication.java and run the program. This will lead to the API to hear for any request on port 8080.

To obtain the response using curl command directly through terminal (port 8080), please make sure to add ` --output NameofPdfFile.pdf` to your curl request to save the pdf to your local directory. (Without this command it will result in the output overloading the terminal).

Some of the output files are saved inside output in demo.

## 2) Resume Builder UI

The UI is implemented using a simple react.js project which converts the input to json and sends the request using fetch API to /resume. (Make sure that the server (API) is up and running and listening). The API returns the pdf file as requestEntity and is saved in blob. The blob saves the pdf file to your local system and creates a fileURL which open in another pdf file. 

The UI implements basic function of choosing the template, inputting personal information, adding multiple schools, experiences etc. On clicking generate pdf button the json is sent to the API and we recieve PDF from the API.

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can't go back!**

If you aren't satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you're on your own.

You don't have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn't feel obligated to use this feature. However we understand that this tool wouldn't be useful if you couldn't customize it when you are ready for it.

## Result

1) The API is created with java and spring-boot
2) UI has been created with React JS


## Adobe Document Generation API

Do you want to open this link: https://developer.adobe.com/document-services/docs/overview/document-generation-api/quickstarts/?

## Contact

Contact me at vaibhav20547@iiitd.ac.in for any doubts!

## Application Look

![App Screenshot](https://github.com/vaibhuujaiswal/Personalised_Resume_Builder/blob/master/screencapture-localhost-3000-2023-07-09-17_48_54.png)

## Final Document Displayed and Downloaded
![App Screenshot](https://github.com/vaibhuujaiswal/Personalised_Resume_Builder/blob/master/FinalDocumentDisplayedAndPrinted.png)

## Loading Screen

![App Screenshot](https://github.com/vaibhuujaiswal/Personalised_Resume_Builder/blob/master/Loader%20Screen.png)
