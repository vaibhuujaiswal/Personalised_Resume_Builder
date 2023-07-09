import logo from './logo.svg';
import './App.css';
import React, { useState } from 'react';
import { saveAs } from 'file-saver/dist/FileSaver';


function App() {
  const [isLoading, setIsLoading] = useState(false);

  function submitResumeRequest() {
    setIsLoading(true);
    // Define the resume request data
    console.log(document.getElementById("template_id").value);
    const data = {
      template_id: document.getElementById("template_id").value,
      personal_information: {
        name: document.getElementById("name").value,
        last_name: document.getElementById("last_name").value,
        email_address: document.getElementById("email_address").value,
        phone_number: document.getElementById("phone_number").value,
        linkedin_url: document.getElementById("linkedin_url").value
      },
      job_title: document.getElementById("job_title").value,
      career_objective: document.getElementById("career_objective").value,
      skills: document.getElementById("skills").value.split("'").filter(Boolean),
      education: getEducationData(),
      experience: getExperienceData(),
      achievements: getAchievementsData(),
    };

    function getEducationData() {
      const educationInputs = document.querySelectorAll('.education-input');
      const educationData = [];
  
      educationInputs.forEach(input => {
        const schoolName = input.querySelector('.school-name').value;
        const passingYear = input.querySelector('.passing-year').value;
        const description = input.querySelector('.description').value;
  
        if (schoolName && passingYear && description) {
          educationData.push({
            school_name: schoolName,
            passing_year: passingYear,
            description: description,
          });
        }
      });
  
      return educationData;
    }

    function getExperienceData() {
      const experienceInputs = document.querySelectorAll('.experience-input');
      const experienceData = [];
  
      experienceInputs.forEach(input => {
        const companyName = input.querySelector('.company-name').value;
        const passingYear = input.querySelector('.passing-year').value;
        const responsibilities = input.querySelector('.responsibilities').value;
  
        if (companyName && passingYear && responsibilities) {
          experienceData.push({
            company_name: companyName,
            passing_year: passingYear,
            responsibilities: responsibilities,
          });
        }
      });
  
      return experienceData;
    }

    function getAchievementsData() {
      const achievementInputs = document.querySelectorAll('.achievement-input');
      const achievementData = [];
  
      achievementInputs.forEach(input => {
        const field = input.querySelector('.field').value;
        const awards = input.querySelector('.awards').value;
  
        if (field && awards) {
          achievementData.push({
            field: field,
            awards: awards,
          });
        }
      });
  
      return achievementData;
    }


  const url = 'http://localhost:8080/resume';
  const headers = {
    'Accept': 'application/pdf',
    'Content-Type': 'application/json',
    'Origin': "http://localhost:8080"
  };

  console.log(data)

  fetch(url, {
    method: 'POST',
    headers: headers,
    body: JSON.stringify(data),
    crossorigin: true
  }) 
    
    .then(response => {
      // Handle the response
      if (response.ok) {
        return response.blob();
      } else {
        throw new Error('HTTP request failed');
      }
    })
    .then(blob => {
      // Save the file using FileSaver.js
      console.log(blob);
      saveAs(blob, 'output.pdf');
      //Create a Blob from the PDF Stream
      // const file = new Blob([blob], { type: "application/pdf" });
      //Build a URL from the file
      
      const fileURL = URL.createObjectURL(blob);
      //Open the URL on new Window
       const pdfWindow = window.open();
       pdfWindow.location.href = fileURL; 
       setIsLoading(false);
    })
    .catch(error => {
      // Handle the error
      console.error('Error:', error.message);
      setIsLoading(false);
      alert('An error occurred during the request due to ' + error.message + 'Please try again later.');
    });
  }


  return (
    <div className="App">
    {/* <meta name="referrer" content="no-referrer" /> */}
    <h3>Template ID</h3>
    <select id="template_id" defaultValue={"2"}>
      <option value="1">Template 1 : Basic Template</option>
      <option value="2">Template 2 : Link Template</option>
      <option value="3">Template 3 : Image Template</option>
    </select>
    <h3>Personal Information</h3>
    <label> Name </label>
    <input id="name" placeholder='Lorem'></input>
    <br/>
    <label>   Last Name   </label>
    <input id="last_name" placeholder='ipsum'></input>
    
    <br/>
    <label> . Email Address </label>
    <input id="email_address" placeholder='ipsum@adobe.com'></input>
    <br/>
    <label> Phone Number </label>
    <input id="phone_number" placeholder='91 99xx14xx99'></input>
    <br/>
    <label> linkedin URL </label>
    <input id="linkedin_url" placeholder='https://www.linkedin.com'></input>
    <br/>
    <label> Job Title </label>
    <input id="job_title" placeholder='Software Development Engineer'></input> 
    <br/>
    <label>Career Objective</label>
    <input id="career_objective" placeholder='Description of Career Objective'></input>  
    <br/>
    <label> Skills</label>
    <textarea id="skills" placeholder='Comma Seperated Values'></textarea>
    <br />
    <h1>Education</h1>
      <div id="education-container">
        <div className="education-input">
          <label> School Name </label>
          <input className="school-name" placeholder='Adobe School'/>
          <br />
          <label> Passing Year </label>
          <input className="passing-year" placeholder='2023' />
          <br />
          <label> Description </label>
          <textarea className="description" placeholder = "Description of activities and so on"></textarea>
        </div>
      </div>
      <button onClick={() => {
        const educationContainer = document.getElementById("education-container");
        const newEducationInput = document.createElement("div");
        newEducationInput.className = "education-input";
        newEducationInput.innerHTML = `
          <label>School Name</label>
          <input class="school-name" />
          <br />
          <label>Passing Year</label>
          <input class="passing-year" />
          <br />
          <label>Description</label>
          <textarea class="description"></textarea>
        `;
        educationContainer.appendChild(newEducationInput);
      }}>Add Education</button>
      <br />
      <h3>Experience</h3>
      <div id="experience-container">
        <div className="experience-input">
          <label> Company Name </label>
          <input className="company-name" placeholder='Adobe'/>
          <br />
          <label> Passing Year </label>
          <input className="passing-year" placeholder='2025'/>
          <br />
          <label> Responsibilities </label>
          <textarea className="responsibilities" placeholder='Cloud Document'></textarea>
        </div>
      </div>
      <button onClick={() => {
        const experienceContainer = document.getElementById("experience-container");
        const newExperienceInput = document.createElement("div");
        newExperienceInput.className = "experience-input";
        newExperienceInput.innerHTML = `
          <label>Company Name</label>
          <input class="company-name" />
          <br />
          <label>Passing Year</label>
          <input class="passing-year" />
          <br />
          <label>Responsibilities</label>
          <textarea class="responsibilities"></textarea>
        `;
        experienceContainer.appendChild(newExperienceInput);
      }}>Add Experience</button>
      <br />
      <h3>Achievements</h3>
      <div id="achievements-container">
        <div className="achievement-input">
          <label> Field </label>
          <input className="field" placeholder='Tech'/>
          <br/>
          <label> Awards </label>
          <input className="awards" placeholder='Adobe PapyrusNebulae Winner 2023'/>
        </div>
      </div>
      <button onClick={() => {
        const achievementsContainer = document.getElementById("achievements-container");
        const newAchievementInput = document.createElement("div");
        newAchievementInput.className = "achievement-input";
        newAchievementInput.innerHTML = `
          <label>Field</label>
          <input class="field" />
          <br />
          <label>Awards</label>
          <input class="awards" />
        `;
        achievementsContainer.appendChild(newAchievementInput);
      }}>Add Achievement</button>
      <br />
    <button onClick={submitResumeRequest}>Generate resume</button>
    {isLoading && <div className="loading">Generating resume...will show in new tab and download to laptop</div>}
    </div>
    
  );
}

export default App;


