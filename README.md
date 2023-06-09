<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->



<!-- PROJECT LOGO -->
<br />
<div align="center">

<h3 align="center">E-SCORE Back-End</h3>

  <p align="center">
     An API that suggests additional security criteria and security mechanism to be used in security requirements in a specific domain.
    <br />
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

E-SCORE is a web-based tool that enhances the security requirements engineering process by providing suggestions for additional security criteria based on a primary criterion. It utilizes the SCORE ontology, which takes into account various domains, security mechanisms, and security criteria to provide comprehensive security recommendations.
This part of the project consists of an API that provides the security mechanisms and security criteria by querying an ontology.
<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

* [![JavaEE][Java]][Java-url]
* [![OWL][OWL]][OWL-url]
* [![apache-jena][apache-jena]][apache-jena-url]
* [![jwt][jwt]][jwt-url]



<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

To start the project you need to follow the next steps:

### Prerequisites

* java
  ```
  https://www.java.com/download/ie_manual.jsp
  ```
* Java server (Wildfly for example)

     ```
     https://www.wildfly.org/
     ```
* Authorization configuration
  Add the Issuer and the Secret you wan to use in the Engine class
    ```java
  public class Engine {
    private final String ISSUER = "<add the issuer of the token>";
    private final String ApiSecret ="<add the secret used to create the token>";

    public String getISSUER() {
        return ISSUER;
    }

    public String getApiSecret() {
        return ApiSecret;
    }

    }
    ```
  
### Installation

* After building the artifact, add it in the deployments folder of the server (standalone -> deployment)
* Or configure the IDE to run the code using the server (Add Configuration)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage
The deployed code will expose an API with the following end-points:
```
- /criteria 
     Inputs : -
     Output: JSON Array - all security criteria in the ontology
- /criteria/{relation}/{criteria}
     Inputs: 
     relation: one of the defined relations between the security criteria
     criteria : one of the 30 defined security criteria 
     Output: JSON Array - all criteria that have a given relationship with a given criterion
- /mechanism
     Inputs: -
     Output: JSON Array - all security mechanisms in the ontology
- /domain
     Inputs: -
     Output: JSON Array - all domains in the ontology
- /domain/ {domain}/mechanisms
     Inputs: 
     domain: one of the domains defined in the ontology
     Output: JSON Array - the security mechanisms of a specific domain in the ontology
- /domain/{domain}/criteria
     Inputs: 
     domain: one of the domains defined in the ontology
     Output: JSON Array- the security criteria of a specific domain in the ontology
- /domain/ {domain}/{criteria}
     Inputs: 
     domain: one of the domains defined in the ontology
     criteria : one of the 30 security criteria defined 
     Output: JSON Array- the security mechanisms of a specific security criterion in a specific domain of the ontology.
- /domain/ {domain}/{relation}/{criteria}
     Inputs: 
     domain: one of the domains defined in the ontology
     relation : one of the relations defined between the security criteria
     criteria : one of the 30 security criteria defined (figure 2)
     Output: JSON Array- the security criteria that have a given relationship with a given security criterion in a given domain of the ontology.
- /domain/ {domain}/{relation}/all
     Inputs: 
     domain: one of the domains defined in the ontology
     relation : one of the relations defined between the security criteria
     Output: JSON Object (Key-Value) - additional security criteria that have a given relationship with all security criteria in a given domain of the ontology. (Key -relation-> value)

```
For further explanation of the SCORE ontology and the use of the E-SCORE web application please refer to the following document:
[Documentation](https://drive.google.com/file/d/1XAEEN-avJmbQm2n6AQFsHIDs9xNKU9_G/view?usp=share_link)


<p align="right">(<a href="#readme-top">back to top</a>)</p>






<!-- CONTRIBUTING -->
## Contributing

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/NewFeature`)
3. Commit your Changes (`git commit -m 'Add some NewFeature'`)
4. Push to the Branch (`git push origin feature/NewFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Hiba Hnaini - hiba-hnaini@hotmail.com

Project Link: [https://github.com/hibahnaini/E-SCORE-Backend](https://github.com/hibahnaini/E-SCORE-Backend)

<p align="right">(<a href="#readme-top">back to top</a>)</p>








<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[Java]:https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.oracle.com/java/technologies/java-ee-glance.html#:~:text=Java%20Platform%2C%20Enterprise%20Edition%20(Java%20EE)%20is%20the%20standard,User%20Groups%2C%20and%20countless%20individuals.
[OWL]:https://img.shields.io/badge/OWL-20232A?style=for-the-badge&logo=OWL&logoColor=61DAFB
[OWL-url]:https://www.w3.org/TR/?tag=data
[apache-jena]:https://img.shields.io/badge/Apache-Jena-20232A?style=for-the-badge&logo=apache-jena&logoColor=61DAFB
[apache-jena-url]:https://jena.apache.org/

[jwt]: https://img.shields.io/badge/jwt-563D7C?style=for-the-badge&logo=jwt&logoColor=61DAFB
[jwt-url]: https://jwt.io/

