Celestial Body:
Masses are measured in Jupiter masses (Mj)
Radii are measured in Jupiter radii (Rj)
Name is either colloquial (“proxima centauri”) or scientific (“Lalande 21185”)

Exoplanet:
type: "superrocky", "rocky", "subrocky", "gas giant", "ice giant"
hasLife: true/false
moons: any integer
temperature: always in kelvin, up to two decimal places
atmosphere: "incredibly minimal", "minimal", "medium", "high", "incredibly high"
parent: parent star (actual object, not just name)

Star:
type: (OBAFGKM) + double (to one decimal place) ex: M5.5, G4.8, O9.1, ect.
colour: surface colour based on surface temperature
planets: # of planets orbiting star, not including unconfirmed planets, moons, asteroids, etc.

Moon:
type: "Rocky", "Ice-covered", "Irregular", "Satelite", "Gaseous"
hasLife, temperature, atmosphere same as exoplanet
parent: an actual Exoplanet object