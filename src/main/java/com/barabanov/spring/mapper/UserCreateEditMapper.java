package com.barabanov.spring.mapper;

import com.barabanov.spring.database.entity.Company;
import com.barabanov.spring.database.entity.User;
import com.barabanov.spring.database.repository.CompanyRepository;
import com.barabanov.spring.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User>
{

    private final CompanyRepository companyRepository;

    @Override
    public User map(UserCreateEditDto object)
    {
        var user = new User();
        copy(object, user);

        return user;
    }

    private void copy(UserCreateEditDto object, User user) {
        user.setUsername(object.getUsername());
        user.setBirthDate(object.getBirthDate());
        user.setFirstname(object.getFirstname());
        user.setLastname(object.getLastname());
        user.setRole(object.getRole());
        user.setCompany(getCompany(object.getCompanyId()));
    }

    @Override
    public User map(UserCreateEditDto fromObject, User toObject)
    {
        copy(fromObject, toObject);
        return toObject;
    }

    private Company getCompany(Integer companyId)
    {
        return Optional.ofNullable(companyId)
                .flatMap(companyRepository::findById)
                .orElse(null);
    }
}
