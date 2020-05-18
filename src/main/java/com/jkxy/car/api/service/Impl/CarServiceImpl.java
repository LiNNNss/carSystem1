package com.jkxy.car.api.service.Impl;

import com.jkxy.car.api.dao.CarDao;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("carService")
public class CarServiceImpl implements CarService {

    private final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Car findById(int id) {
        return carDao.findById(id);
    }

    @Override
    public List<Car> findByCarName(String carName) {
        return carDao.findByCarName(carName);
    }

    @Override
    public void deleteById(int id) {
        carDao.deleteById(id);
    }

    @Override
    public void updateById(Car car) {
        carDao.updateById(car);
    }

    @Override
    public void insertCar(Car car) {
        carDao.insertCar(car);
    }
    @Override
    public void buyCar(Car car) {
        //顾客购买车辆数量信息
        Integer num1 = car.getCarQuantity();
        //库存车辆数量
        Integer num2 = carDao.findById(car.getId()).getCarQuantity();

        if(num1 > num2){
           logger.error("购买车辆已超出库存数量");
        }
        Integer num = num2 - num1;
        car.setCarQuantity(num);
        carDao.buyCar(car);
    }

    @Override
    public List<Car> findByCarType( String name, int beginNum, int endNum) {
        return carDao.findByCarType(name,beginNum,endNum);
    }
}
