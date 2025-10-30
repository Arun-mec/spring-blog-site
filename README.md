## Blog Website

#### Description:

A full-stack Blog Management System designed to enable users to create, categorize, and manage blog content efficiently. 
The system supports user authentication using JWT, and provides APIs for managing Posts, Tags, Categories, and Users. 
It follows a clean modular architecture and supports role-based access for secure content management.

#### Key Features

User Authentication: Secure login with JWT-based authorization.<br />
Post Management: Create, update, delete, and view posts. Supports draft and published states.<br />
Category Management: Organize posts under specific categories.<br />
Tag System: Associate multiple tags with posts for flexible content filtering.<br />
Role-based Access: Restricts post creation and modification to authenticated users.<br />
RESTful APIs: Clean, predictable endpoints following REST conventions.<br />

#### Core Entities

Post → Represents blog content (title, content, author, tags, category, status).<br />
Tag → Describes keywords associated with posts.<br />
Category → Groups posts by topic.<br />
User → Handles authentication and role management.<br />

#### Tech Stack

Backend: Spring Boot, Java, Spring Security, JWT, JPA/Hibernate<br />
Database: PostgreSQL<br />
Build Tool: Maven<br />
Version Control: Git & GitHub<br />

#### API description

POST	/api/v1/auth	Authenticate user and recieve JWT token<br />

GET	/api/v1/posts	Get all published posts<br />
POST	/api/v1/posts	Create a new post<br />
GET	/api/v1/posts/{id}	Get a post<br />
PUT	/api/v1/posts/{id}	Update a post<br />
DELETE	/api/v1/posts/{id}	Delete a post<br />
GET	/api/v1/posts/drafts	Get all draft post authenticated user<br />
		
GET	/api/v1/categories	Get all categories<br />
POST	/api/v1/categories	Create a ceteogry<br />
DELETE	/api/v1/categories/{id}	Delete a category<br />
		
GET	/api/v1/tags	Get all tags<br />
POST	/api/v1/tags	Create a tag<br />
DELETE	/api/v1/tags/{id}	Delete a tag<br />
