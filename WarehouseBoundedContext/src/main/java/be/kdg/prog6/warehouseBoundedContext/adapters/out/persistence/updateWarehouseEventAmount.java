//package be.kdg.prog6.boundedcontextB.adapters.out.persistence.Adapters;
//public WarehouseEvent updateWarehouseEventAmount(WarehouseEventId eventId, double newAmount) {
//    // Fetch the event by its ID
//    Optional<WarehouseEventEntity> optionalEvent = eventRepository.findById(eventId.id());
//
//    if (optionalEvent.isPresent()) {
//        // Update the endWeight
//        WarehouseEventEntity eventEntity = optionalEvent.get();
//        eventEntity.setAmount(newAmount);  // Update the weighInTime, e.g., 50
//
//        // Save the updated event
//        eventRepository.save(eventEntity);
//
//        // Return the updated domain model
//        return modelMapper.map(eventEntity, WarehouseEvent.class);
//    }
//
//    throw new RuntimeException("WarehouseEvent not found with ID: " + eventId);
//}
