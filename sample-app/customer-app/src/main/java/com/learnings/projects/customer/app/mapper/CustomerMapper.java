package com.learnings.projects.customer.app.mapper;

import com.learnings.projects.customer.app.entity.CustomerEntity;
import com.learnings.projects.customer.app.entity.RelatedPartyEntity;
import com.learnings.projects.customer.app.entity.ContactMediumValue;
import com.learnings.projects.customer.model.ContactMedium;
import com.learnings.projects.customer.model.Customer;
import com.learnings.projects.customer.model.CustomerCreate;
import com.learnings.projects.customer.model.RelatedParty;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {

    public CustomerEntity toEntity(CustomerCreate source) {
        CustomerEntity target = new CustomerEntity();
        target.setName(source.getName());
        target.setStatus(source.getStatus());
        target.setExternalId(source.getExternalId());
        target.setDescription(source.getDescription());
        return target;
    }

    public CustomerEntity toEntity(Customer source) {
        CustomerEntity target = new CustomerEntity();
        target.setId(source.getId());
        target.setHref(source.getHref());
        target.setName(source.getName());
        target.setStatus(source.getStatus());
        target.setExternalId(source.getExternalId());
        target.setDescription(source.getDescription());
        return target;
    }

    public Customer toModel(CustomerEntity source) {
        Customer target = new Customer();
        target.setId(source.getId());
        target.setHref(source.getHref());
        target.setName(source.getName());
        target.setStatus(source.getStatus());
        target.setExternalId(source.getExternalId());
        target.setDescription(source.getDescription());
        target.setPartyRole(toPartyModels(source.getPartyRole()));
        target.setContactMedium(toContactModels(source.getContactMedium()));
        return target;
    }

    public List<Customer> toListResponse(List<CustomerEntity> entities) {
        List<Customer> customers = new ArrayList<>();
        for (CustomerEntity entity : entities) {
            customers.add(toModel(entity));
        }
        return customers;
    }

    public void merge(CustomerEntity target, Customer source) {
        target.setName(source.getName());
        target.setStatus(source.getStatus());
        target.setExternalId(source.getExternalId());
        target.setDescription(source.getDescription());
    }

    public void syncRelated(CustomerEntity target, Customer source) {
        target.getPartyRole().clear();
        if (source.getPartyRole() != null) {
            for (RelatedParty party : source.getPartyRole()) {
                RelatedPartyEntity entity = new RelatedPartyEntity();
                entity.setExternalId(party.getId());
                entity.setName(party.getName());
                entity.setRole(party.getRole());
                entity.setCustomer(target);
                target.getPartyRole().add(entity);
            }
        }

        target.getContactMedium().clear();
        if (source.getContactMedium() != null) {
            for (ContactMedium medium : source.getContactMedium()) {
                ContactMediumValue entity = new ContactMediumValue();
                entity.setType(medium.getType());
                entity.setPreferred(Boolean.TRUE.equals(medium.getPreferred()));
                entity.setMedium(medium.getMedium());
                target.getContactMedium().add(entity);
            }
        }
    }

    private List<RelatedParty> toPartyModels(List<RelatedPartyEntity> entities) {
        List<RelatedParty> result = new ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (RelatedPartyEntity entity : entities) {
            RelatedParty model = new RelatedParty();
            model.setId(entity.getExternalId());
            model.setName(entity.getName());
            model.setRole(entity.getRole());
            result.add(model);
        }
        return result;
    }

    private List<ContactMedium> toContactModels(List<ContactMediumValue> entities) {
        List<ContactMedium> result = new ArrayList<>();
        if (entities == null) {
            return result;
        }
        for (ContactMediumValue entity : entities) {
            ContactMedium model = new ContactMedium();
            model.setType(entity.getType());
            model.setPreferred(entity.isPreferred());
            model.setMedium(entity.getMedium());
            result.add(model);
        }
        return result;
    }
}
