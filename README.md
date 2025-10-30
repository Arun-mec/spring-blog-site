## Blog Website

#### Description:

A full-stack Blog Management System designed to enable users to create, categorize, and manage blog content efficiently. 
The system supports user authentication using JWT, and provides APIs for managing Posts, Tags, Categories, and Users. 
It follows a clean modular architecture and supports role-based access for secure content management.

#### Key Features

User Authentication: Secure login with JWT-based authorization.
Post Management: Create, update, delete, and view posts. Supports draft and published states.
Category Management: Organize posts under specific categories.
Tag System: Associate multiple tags with posts for flexible content filtering.
Role-based Access: Restricts post creation and modification to authenticated users.
RESTful APIs: Clean, predictable endpoints following REST conventions.

#### Core Entities

Post → Represents blog content (title, content, author, tags, category, status).
Tag → Describes keywords associated with posts.
Category → Groups posts by topic.
User → Handles authentication and role management.

#### Tech Stack

Backend: Spring Boot, Java, Spring Security, JWT, JPA/Hibernate
Database: PostgreSQL
Build Tool: Maven
Version Control: Git & GitHub

#### API description

POST	/api/v1/auth	Authenticate user and recieve JWT token

GET	/api/v1/posts	Get all published posts
POST	/api/v1/posts	Create a new post
GET	/api/v1/posts/{id}	Get a post
PUT	/api/v1/posts/{id}	Update a post
DELETE	/api/v1/posts/{id}	Delete a post
GET	/api/v1/posts/drafts	Get all draft post authenticated user
		
GET	/api/v1/categories	Get all categories
POST	/api/v1/categories	Create a ceteogry
DELETE	/api/v1/categories/{id}	Delete a category
		
GET	/api/v1/tags	Get all tags
POST	/api/v1/tags	Create a tag
DELETE	/api/v1/tags/{id}	Delete a tag
