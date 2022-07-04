docker run --rm --name learningmanagementsystem -e DATABASE_USERNAME=lmsuser -e DATABASE_PASSWORD=lmspw -e DATABASE_URL=jdbc:h2:mem:lms -p 8080:8080 -it learningmanagementsystem
pause