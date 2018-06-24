# JLaTexRS
A open source Java implementation of LaTex Reporting Services based on the specifications found [here](https://github.com/campell1212/LaTexRS).

## Install
To install JLaTexRS...
## Accessing
### Adding/Removing reports
1. Navigate to the administration web interface found usually [http://localhost:5454/admin](http://localhost:5454/admin)
2. In the interface ....
### Viewing reports
All reports can be viewed either through the web interface or using the inbuilt report browser
#### File interface
The reports can be accessed through a normal file interface with the PDF/HTML version of the report (without the control bar) or the report with the control bar (page navigation, additional downloads, etc...). The default URL is [http://hostname/rs](http://hostname/reports).
#### Web interface
The reports can also be found 
### Advance configuration
Advance configuration can be accessed through the admin interface [http://localhost:5454/admin](http://localhost:5454/admin) by clicking on the cog. More information about the configuration page can be found [here](https://putinurl.com).
## API
We have an API also! You can do most of the administration functionality via the RESTful API.
### Adding reports
> http://HOSTNAME:5454/api/add
### Listing reports
### Removing reports
### Modifying directory structure
### Add/remove user


