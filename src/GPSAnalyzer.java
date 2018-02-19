import java.util.Objects;

class GPSAnalyzer {
   // attributs
   double latitude;
   double longitude;
   double altitude;

   public GPSAnalyzer() {
      this.latitude = 0;
      this.longitude = 0;
      this.altitude = 0;
   }

   public void analyzeFrame(String frame) {
      String[] gngga = frame.split(",");
      if(Objects.equals(gngga[0], "$GNGGA")) {
         if(frame.length() == 15) {
            if(Objects.equals(gngga[3], "N") && Objects.equals(gngga[5], "E")) {
               this.latitude = Double.parseDouble(gngga[2]);
               this.longitude = Double.parseDouble(gngga[4]);
               this.altitude = Double.parseDouble(gngga[9]);
            }
         }
      }
   }
}
