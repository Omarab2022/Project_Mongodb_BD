package com.Mongo.LocationVoiture.Repository;

import com.Mongo.LocationVoiture.entity.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CarRepo extends MongoRepository<Car,String> {

	@Query("{'_id' : ?0}")
	Car findCarById(String id);

	@Query("{'_id' : ?0}")
	void updateCar(String id, Car updatedCar);



	@Query("{ $or: [ { 'end_Date' : { $gte: ?0 }, 'start_Date' : { $lte: ?0 }," +
			" 'available' : true }, { 'end_Date' : { $gte: ?1 }, 'start_Date' : { $lte: ?1 }, 'available' : true } ] }")
	List<Car> findByStartOrEndDateAndAvailability(Date date1, Date date2);


	@Query(value = "{'id': ?0}", delete = true)
	void deleteById(String id);

	@Query("{$or:[{'model': {$regex : ?0, $options: 'i'}},{'brand': {$regex : ?0, $options: 'i'}}," +
			"{'description': {$regex : ?0, $options: 'i'}}]}")
	List<Car> searchCarsByKeyword(String keyword);

	@Query("{ 'available' : ?0 }")
	Integer countByAvailable(boolean b);


	@Query(value = "{}", fields = "{ brand : 1, _id: 0 }")
	List<String> findDistinctBrands();

	@Query("{ brand: ?0 }")
	List<Car> findByBrand(String brand);


	@Query("{ 'end_Date' : { $gte: ?0 }, 'start_Date' : { $lte: ?1 }, 'available' : true }")
	List<Car> findByStartAndEndDateAndAvailability(Date end_Date, Date start_Date);


}
