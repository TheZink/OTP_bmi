FROM maven:latest
LABEL authors="ilkkasin"

# Set working directory
WORKDIR /app

# Install GUI libraries
RUN apt-get update && apt-get install -y \
    libx11-6 libxext6 libxrender1 libxtst6 libxi6 libgtk-3-0 mesa-utils wget unzip \
    && rm -rf /var/lib/apt/lists/*

# Download and unzip JavaFX Linux SDK
RUN mkdir -p /javafx-sdk \
    && wget -O javafx.zip https://download2.gluonhq.com/openjfx/21.0.2/openjfx-21.0.2_linux-x64_bin-sdk.zip \
    && unzip javafx.zip -d /javafx-sdk \
    && mv /javafx-sdk/javafx-sdk-21.0.2/lib /javafx-sdk/lib \
    && rm -rf /javafx-sdk/javafx-sdk-21.0.2 javafx.zip


COPY target/OTP_BMI-1.0-SNAPSHOT-shaded.jar app.jar

# Set X11 display (Windows host with Xming/X11)
ENV DISPLAY=host.docker.internal:0.0

CMD [ "java", "--module-path", "/javafx-sdk/lib", "--add-modules", "javafx.controls,javafx.fxml", "-jar", "target/OTP_BMI-1.0-SNAPSHOT-shaded.jar" ]