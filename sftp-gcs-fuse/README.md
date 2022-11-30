# sftp-gcs-fuse

SFTP access to GCS via a GCS FUSE mount

## Installation

Container image is hosted on Docker Hub at:
gcpceteam/sftp-gcs-fuse

### Modify `startup_script.sh` as needed
1. Create users and passwords for controlling access
2. Mount the relevant GCS Buckets to their respective mount points


### Create VM
1. Go to "Compute Engine --> VM Instances --> Create VM"
2. Select the container above to deploy
3. Select "Advanced Options"
4. In the "Automation" section, paste the contents of `startup_script.sh`
5. Finish creating the VM
6. Ensure VM is running on publicly accessible IP address

