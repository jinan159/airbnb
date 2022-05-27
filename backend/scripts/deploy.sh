sudo chmod +x /home/ec2-user/app/build/libs/*.jar

cd /home/ec2-user/app

sudo docker-compose down
sudo docker-compose build
sudo docker-compose up -d
